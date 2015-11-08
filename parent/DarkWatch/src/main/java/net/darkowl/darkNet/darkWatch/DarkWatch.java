/**
 * 
 */
package net.darkowl.darkNet.darkWatch;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.URISyntaxException;
import java.net.URL;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import net.darkowl.darkNet.darkObjects.config.Configuration;
import net.darkowl.darkNet.darkObjects.interfaces.DarkDevice;
import net.darkowl.darkNet.darkObjects.interfaces.Monitorable;
import net.darkowl.darkNet.darkObjects.xml.config.Darknet;
import net.darkowl.darkNet.darkObjects.xml.config.DeviceType;
import net.darkowl.darkNet.darkWatch.config.WatchConfig;
import net.darkowl.darkNet.darkWatch.exceptions.DarkWatchException;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Main Class that will handle the monitoring of the Dark Net
 * 
 * @author Randy Blancett
 * @since Oct 30, 2015
 * 
 */
public class DarkWatch {
	private final static Logger LOGGER = LogManager.getLogger(DarkWatch.class);
	private static Options options = new Options();
	private static boolean run = true;
	static {
		for (final CommandLineOptons option : CommandLineOptons.values()) {
			DarkWatch.options.addOption(option.getFlag(), option.getFull(),
					option.hasArg(), option.getDescription());
		}
	}

	/**
	 * Get a DarkDevice instance form a class Name
	 * 
	 * @since Nov 6, 2015
	 * @param className
	 *            Name of the class to get an instance of
	 * @param name
	 *            Name of the device
	 * @return DarkDevice
	 * @throws DarkWatchException
	 *             If the class does not implement DarkDevice or there is
	 *             another error
	 */
	@SuppressWarnings("unchecked")
	protected static DarkDevice getInstance(String className, String name)
			throws DarkWatchException {
		if (className == null || className.isEmpty()) {
			throw new DarkWatchException(
					"Class name can not be null or an empty string.");
		}
		DarkDevice deviceInstance = null;
		Class<DarkDevice> device;
		try {
			device = (Class<DarkDevice>) Class.forName(className);
			final Constructor<DarkDevice> constructor = device
					.getConstructor(String.class);
			deviceInstance = constructor.newInstance(name);
		} catch (final ClassNotFoundException e) {
			throw new DarkWatchException("Class: " + className
					+ " is not a valid class (can not be found)");
		} catch (final ClassCastException e) {
			throw new DarkWatchException("Class: " + className
					+ " is not an instance of DarkDevice");
		} catch (NoSuchMethodException | SecurityException
				| InstantiationException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException e) {
			throw new DarkWatchException("Failed to create DarkDevice: ", e);
		}

		return deviceInstance;
	}

	/**
	 * @since Oct 30, 2015
	 * @param args
	 *            Command Line arguments
	 */
	public static void main(String[] args) {
		DarkWatch.LOGGER.info("I am watching you...");
		try {
			new DarkWatch(args);
		} catch (final IOException e) {
			DarkWatch.LOGGER.error("IO Exception", e);
		} catch (final ParseException e) {
			DarkWatch.LOGGER.error("Failed to parse Command Line Arguments", e);
		} catch (final DarkWatchException e) {
			DarkWatch.LOGGER.error(e.getMessage());
		}
		DarkWatch.LOGGER.info("... You are no longer being watched.");
	}

	private static void printHelp() {
		final HelpFormatter formatter = new HelpFormatter();
		formatter.printHelp("darkWatch", DarkWatch.options);
	}

	private static void printVersion() {
		DarkWatch.LOGGER.info(WatchConfig.getVersionString());
	}

	private Darknet netConfig = null;

	/**
	 * @since Oct 30, 2015
	 * @param args
	 *            Command Line Arguments
	 * @throws IOException
	 *             Normally an error opening Propfile
	 * @throws DarkWatchException
	 * @throws ParseException
	 */
	public DarkWatch(String[] args) throws IOException, DarkWatchException,
			ParseException {
		final CommandLineParser parser = new DefaultParser();
		final CommandLine cmd = parser.parse(DarkWatch.options, args);

		if (cmd.hasOption(CommandLineOptons.HELP.getFlag())) {
			DarkWatch.printHelp();
			return;
		}
		this.setConfig(cmd);
		WatchConfig.init();
		if (cmd.hasOption(CommandLineOptons.VERSION.getFlag())) {
			DarkWatch.printVersion();
			return;
		}

		this.loadXmlConfig(Configuration
				.getString(WatchConfig.PROPERTY_XML_FILE_LOCATION));

		this.loadNet();

		while (run) {

		}
	}

	protected static void kill() {
		run = false;
	}

	/**
	 * get the object the describes the configration of the Dark Net
	 * 
	 * @since Nov 4, 2015
	 * @return DarkNet object build from XML
	 */
	public Darknet getNetConfig() {
		return this.netConfig;
	}

	private void loadNet() {
		if (this.netConfig.getDevices() == null) {
			return;
		}
		for (final DeviceType deviceInfo : this.netConfig.getDevices()
				.getDevice()) {
			DarkDevice device;
			try {
				device = getInstance(deviceInfo.getType(), deviceInfo.getName());
				if (device instanceof Monitorable) {
					DarkScheduler.schedule((Monitorable) device);
				}
			} catch (DarkWatchException e) {
				LOGGER.error(
						"Failed to load Device name: " + deviceInfo.getName(),
						e);
			}

		}
	}

	private void loadXmlConfig(String xmlPath) throws DarkWatchException {
		if (xmlPath == null || xmlPath.isEmpty()) {
			throw new DarkWatchException(
					"You are trying to load a blank xml path");
		}
		final File xml = new File(xmlPath);
		if (!xml.exists()) {
			throw new DarkWatchException(
					"You are trying to load the xml config from: "
							+ xml.getPath()
							+ " that file location does not exist.");
		}

		try {
			final JAXBContext jaxbContext = JAXBContext
					.newInstance(Darknet.class);
			final Unmarshaller jaxbUnmarshaller = jaxbContext
					.createUnmarshaller();
			this.netConfig = (Darknet) jaxbUnmarshaller.unmarshal(xml);
		} catch (final JAXBException e) {
			DarkWatch.LOGGER.error("Failed to parse XML", e);
			throw new DarkWatchException(
					"You are trying to load the xml config from: "
							+ xml.getPath() + " that File is not parseable.");
		}

	}

	private void setConfig(CommandLine cmd) {
		if (cmd.hasOption(CommandLineOptons.CONFIG.getFlag())) {
			System.setProperty(Configuration.PROPERTY_CONFIG_PATH,
					cmd.getOptionValue(CommandLineOptons.CONFIG.getFlag()));
		}
	}
}
