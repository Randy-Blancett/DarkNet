/**
 * 
 */
package net.darkowl.darkNet.darkObjects.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map.Entry;
import java.util.Properties;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author Randy Blancett
 * @since Oct 31, 2015
 * 
 */
public class Configuration {
	protected static boolean loaded = false;

	private final static Logger LOGGER = LogManager
			.getLogger(Configuration.class);
	protected static Properties properties = (Properties) System
			.getProperties().clone();
	/**
	 * Date of the Dark Objects Build
	 */
	public static final String PROPERTY_BUILD_DATE_DARK_OBJECTS = "darkObjects.build.date";

	/**
	 * Location of the build info
	 */
	public static final String PROPERTY_BUILD_FILE_LOCATION_DARK_OBJECTS = "darkObjects.version.location";

	/**
	 * Version of the Dark Objects
	 */
	public static final String PROPERTY_BUILD_VERSION_DARK_OBJECTS = "darkObjects.version";
	/**
	 * The path to the default configuration properties file
	 */
	public static final String PROPERTY_CONFIG_PATH = "configuration.path";
	/**
	 * This is the location where DB files will be located (if required for the
	 * DB driver)
	 */
	public static final String PROPERTY_DB_FILE_LOCATION = "db.file.location";
	private static final String VERSION_FILE_NAME = "/DarkObjectsVersion.info";

	public static void clearProperties() {
		Configuration.properties = System.getProperties();
		Configuration.loaded = false;
	}

	/**
	 * Get a property as a string
	 * 
	 * @since Oct 31, 2015
	 * @param property
	 *            The name of the property you want to pull
	 * @return Value of a property
	 */
	public static String getString(String property) {
		return Configuration.properties.getProperty(property);
	}

	/**
	 * Get a property as an Integer
	 * 
	 * @since Jan 15, 2016
	 * @param property
	 * @return
	 */
	public static Integer getInteger(String property) {
		String rawData = Configuration.properties.getProperty(property);
		try {
			return Integer.parseInt(rawData);
		} catch (NumberFormatException e) {
			LOGGER.error("Failed to parse: " + rawData, e);
			return 0;
		}
	}

	/**
	 * Get a string holding the version information
	 * 
	 * @since Oct 31, 2015
	 * @return String containing version information
	 */
	public static String getVersionString() {
		return Configuration
				.getVersionString(
						"Dark Objects",
						new ImmutablePair<String, String>(
								"Version",
								Configuration
										.getString(Configuration.PROPERTY_BUILD_VERSION_DARK_OBJECTS)),
						new ImmutablePair<String, String>(
								"Date",
								Configuration
										.getString(Configuration.PROPERTY_BUILD_DATE_DARK_OBJECTS)));
	}

	/**
	 * Builds a string output form individual components
	 * 
	 * @since Oct 31, 2015
	 * @param library
	 *            The name of the library
	 * @param parts
	 *            Array of the names of the version parts
	 * @return return a formated string of version
	 */
	@SafeVarargs
	protected static String getVersionString(String library,
			Pair<String, String>... parts) {
		final StringBuilder output = new StringBuilder();

		output.append("---------- ");
		output.append(library);
		output.append(" ----------\n");
		for (final Pair<String, String> part : parts) {
			output.append("+   ");
			output.append(part.getKey());
			output.append(": ");
			output.append(part.getValue());
			output.append("\n");
		}
		return output.toString();
	}

	/**
	 * Load properties to memory
	 * 
	 * @since Oct 31, 2015
	 * @throws IOException
	 *             Exception if we cant load the property file
	 */
	public static void init() throws IOException {
		Configuration.setProp(
				Configuration.PROPERTY_BUILD_FILE_LOCATION_DARK_OBJECTS,
				Configuration.VERSION_FILE_NAME, false);
		Configuration.loadEnvProperties();
		Configuration.loadBuildProperties();
		Configuration.loadConfigFile(Configuration
				.getString(Configuration.PROPERTY_CONFIG_PATH));

		Configuration.loaded = true;
	}

	/**
	 * Load properties related to the build
	 * 
	 * @since Nov 3, 2015
	 * @throws IOException
	 *             If There is a problem opening the properties file
	 */
	protected static void loadBuildProperties() throws IOException {
		final InputStream versionInfo = Configuration.class
				.getResourceAsStream(Configuration
						.getString(Configuration.PROPERTY_BUILD_FILE_LOCATION_DARK_OBJECTS));
		try {
			Configuration.LOGGER.trace("Version info:"
					+ Configuration.class
							.getResource(Configuration.VERSION_FILE_NAME));
			if (versionInfo != null) {
				Configuration.properties.load(versionInfo);
			}
		} finally {
			if (versionInfo != null) {
				versionInfo.close();
			}
		}
	}

	/**
	 * Load properties from a file
	 * 
	 * @since Nov 3, 2015
	 * @param path
	 *            path to the properties file
	 * @throws FileNotFoundException
	 *             if the file doesnt exist
	 * @throws IOException
	 *             if it cant load data from config file
	 */
	protected static void loadConfigFile(String path)
			throws FileNotFoundException, IOException {
		if (path == null || path.isEmpty()) {
			Configuration.LOGGER
					.error("Can not load configuration form an empty path.");
			return;
		}
		final File configFile = new File(path);
		if (!configFile.exists()) {
			Configuration.LOGGER.error(configFile.getAbsolutePath()
					+ " does not exist.");
			return;
		}
		final FileInputStream fis = new FileInputStream(configFile);
		try {
			Configuration.properties.load(fis);
		} finally {
			if (fis != null) {
				fis.close();
			}
		}
	}

	/**
	 * Load enviroment properties
	 * 
	 * @since Nov 3, 2015
	 */
	protected static void loadEnvProperties() {
		for (final Entry<String, String> prop : System.getenv().entrySet()) {
			Configuration.properties
					.setProperty(prop.getKey(), prop.getValue());
		}
	}

	/**
	 * Load properties directly from an input Stream
	 * 
	 * @since Nov 7, 2015
	 * @param input
	 *            Input stream of a properties file
	 * @param force
	 *            If a value is already set this will over write it
	 * @throws IOException
	 *             Throws exception if it cant open properties file
	 */
	protected static void loadProps(InputStream input, boolean force)
			throws IOException {
		if (force) {
			Configuration.properties.load(input);
		} else {
			final Properties temp = new Properties();
			temp.load(input);
			for (final Entry<Object, Object> prop : temp.entrySet()) {
				if (Configuration.getString(prop.getKey().toString()) == null) {
					Configuration.properties.setProperty(prop.getKey()
							.toString(), prop.getValue().toString());
				}
			}
		}

	}

	/**
	 * set a property value
	 * 
	 * @since Nov 3, 2015
	 * @param key
	 *            property key
	 * @param value
	 *            property value
	 * @param overwrite
	 *            overwrite if value is not null
	 */
	public static void setProp(String key, String value, boolean overwrite) {
		if (overwrite || Configuration.properties.get(key) == null) {
			Configuration.properties.setProperty(key, value);
		}
	}

}
