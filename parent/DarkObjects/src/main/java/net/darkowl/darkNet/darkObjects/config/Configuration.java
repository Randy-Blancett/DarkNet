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
	/**
	 * Date of the Dark Objects Build
	 */
	public static final String PROPERTY_BUILD_DATE_DARK_OBJECTS = "darkObjects.build.date";

	/**
	 * Version of the Dark Objects
	 */
	public static final String PROPERTY_BUILD_VERSION_DARK_OBJECTS = "darkObjects.version";
	public static final String PROPERTY_BUILD_FILE_LOCATION_DARK_OBJECTS = "darkObjects.version";
	/**
	 * The path to the default configuration properties file
	 */
	public static final String PROPERTY_CONFIG_PATH = "configuration.path";

	protected static boolean loaded = false;

	private final static Logger LOGGER = LogManager
			.getLogger(Configuration.class);
	protected static Properties properties = (Properties) System
			.getProperties().clone();
	private static final String VERSION_FILE_NAME = "/DarkObjectsVersion.info";

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

	protected static void clearProperties() {
		properties = System.getProperties();
		loaded = false;
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
	 * Load properties related to the build
	 * 
	 * @since Nov 3, 2015
	 * @throws IOException
	 */
	protected static void loadBuildProperties() throws IOException {
		final InputStream versionInfo = Configuration.class
				.getResourceAsStream(getString(PROPERTY_BUILD_FILE_LOCATION_DARK_OBJECTS));
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
	protected static void setProp(String key, String value, boolean overwrite) {
		if (overwrite || properties.get(key) == null) {
			properties.setProperty(key, value);
		}
	}

	/**
	 * Load properties to memory
	 * 
	 * @since Oct 31, 2015
	 * @throws IOException
	 *             Exception if we cant load the property file
	 */
	public static void init() throws IOException {
		setProp(PROPERTY_BUILD_FILE_LOCATION_DARK_OBJECTS,
				Configuration.VERSION_FILE_NAME, false);
		Configuration.loadEnvProperties();
		Configuration.loadBuildProperties();
		loadConfigFile(getString(PROPERTY_CONFIG_PATH));

		Configuration.loaded = true;
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

}
