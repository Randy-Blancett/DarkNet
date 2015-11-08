/**
 * 
 */
package net.darkowl.darkNet.darkWatch.config;

import java.io.IOException;
import java.io.InputStream;

import net.darkowl.darkNet.darkObjects.config.Configuration;
import net.darkowl.darkNet.darkWatch.DarkWatch;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author Randy Blancett
 * @since Oct 31, 2015
 * 
 */
public class WatchConfig extends Configuration {
	/**
	 * Date Data Watch was built
	 */
	public static final String PROPERTY_BUILD_DATE_DARK_WATCH = "darkWatch.build.date";
	/**
	 * Version of Data Watch
	 */
	public static final String PROPERTY_BUILD_VERSION_DARK_WATCH = "darkWatch.version";
	/**
	 * The location where the program can find the XML configration file;
	 */
	public static final String PROPERTY_XML_FILE_LOCATION = "config.xml.location";

	private final static Logger LOGGER = LogManager
			.getLogger(Configuration.class);

	private static final String VERSION_FILE_NAME = "/DarkWatchVersion.info";

	/**
	 * Get a string holding the version information
	 * 
	 * @since Oct 31, 2015
	 * @return get the version string
	 */
	public static String getVersionString() {
		return Configuration
				.getVersionString(
						"Dark Watch",
						new ImmutablePair<String, String>(
								"Version",
								Configuration
										.getString(WatchConfig.PROPERTY_BUILD_VERSION_DARK_WATCH)),
						new ImmutablePair<String, String>(
								"Date",
								Configuration
										.getString(WatchConfig.PROPERTY_BUILD_DATE_DARK_WATCH)))
				+ Configuration.getVersionString();
	}

	/**
	 * Load properties to memory
	 * 
	 * @since Oct 31, 2015
	 * @throws IOException
	 *             Error opening properties
	 */
	public static void init() throws IOException {
		try {
			Configuration.loadProps(DarkWatch.class
					.getResourceAsStream("/default.properties"));
		} catch (IOException e) {
			LOGGER.error("Failed to load Default Properties",e);
		}
		Configuration.init();

		final InputStream versionInfo = WatchConfig.class
				.getResourceAsStream(WatchConfig.VERSION_FILE_NAME);
		WatchConfig.LOGGER.trace("Version info:"
				+ WatchConfig.class.getResource(WatchConfig.VERSION_FILE_NAME));
		if (versionInfo != null) {
			Configuration.properties.load(versionInfo);
		}
	}
}
