/**
 * 
 */
package net.darkowl.darkNet.darkWatch;

import java.io.IOException;

import net.darkowl.darkNet.darkWatch.config.WatchConfig;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author Randy Blancett
 * @since Oct 30, 2015
 * 
 */
public class DarkWatch {
	private final static Logger LOGGER = LogManager.getLogger(DarkWatch.class);
	private static Options options = new Options();

	static {
		for (final CommandLineOptons option : CommandLineOptons.values()) {
			DarkWatch.options.addOption(option.getFlag(), option.getFull(),
					option.hasArg(), option.getDescription());
		}
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

	/**
	 * @since Oct 30, 2015
	 * @param args
	 *            Command Line Arguments
	 * @throws IOException
	 *             Normally an error opening Propfile
	 */
	public DarkWatch(String[] args) throws IOException {
		final CommandLineParser parser = new DefaultParser();
		try {
			final CommandLine cmd = parser.parse(DarkWatch.options, args);

			if (cmd.hasOption(CommandLineOptons.HELP.getFlag())) {
				DarkWatch.printHelp();
				return;
			}

			setConfig(cmd);

			WatchConfig.init();

			if (cmd.hasOption(CommandLineOptons.VERSION.getFlag())) {
				DarkWatch.printVersion();
				return;
			}
		} catch (final ParseException e) {
			DarkWatch.LOGGER.error("Failed to parse Command Line Arguments", e);
		}
	}

	private void setConfig(CommandLine cmd) {
		if (cmd.hasOption(CommandLineOptons.CONFIG.getFlag())) {
			System.setProperty(WatchConfig.PROPERTY_CONFIG_PATH,
					cmd.getOptionValue(CommandLineOptons.CONFIG.getFlag()));
		}
	}
}
