/**
 * 
 */
package net.darkowl.darkNet.darkObjects.config;

import java.io.IOException;
import java.io.InputStream;
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
	public static final String BUILD_DATE_DARK_OBJECTS = "darkObjects.build.date";

	/**
	 * Version of the Dark Objects
	 */
	public static final String BUILD_VERSION_DARK_OBJECTS = "darkObjects.version";
	protected static boolean loaded = false;

	private final static Logger LOGGER = LogManager
			.getLogger(Configuration.class);
	protected static Properties properties = new Properties();
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

	/**
	 * Get a string holding the version information
	 * 
	 * @since Oct 31, 2015
	 * @return String containing version information
	 */
	public static String getVersionString() {
		return Configuration.getVersionString(
				"Dark Objects",
				new ImmutablePair<String, String>("Version", Configuration
						.getString(Configuration.BUILD_VERSION_DARK_OBJECTS)),
				new ImmutablePair<String, String>("Date", Configuration
						.getString(Configuration.BUILD_DATE_DARK_OBJECTS)));
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
		final InputStream versionInfo = Configuration.class
				.getResourceAsStream(Configuration.VERSION_FILE_NAME);
		Configuration.LOGGER.trace("Version info:"
				+ Configuration.class
						.getResource(Configuration.VERSION_FILE_NAME));
		if (versionInfo != null) {
			Configuration.properties.load(versionInfo);
		}
		Configuration.loaded = true;
	}

}
