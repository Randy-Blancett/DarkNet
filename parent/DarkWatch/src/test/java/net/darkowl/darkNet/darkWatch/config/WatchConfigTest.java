/**
 * 
 */
package net.darkowl.darkNet.darkWatch.config;

import java.io.IOException;

import net.darkowl.darkNet.darkObjects.config.Configuration;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Randy Blancett
 * @since Oct 31, 2015
 * 
 */
public class WatchConfigTest {

	@Before
	public void setUp() {
		try {
			WatchConfig.init();
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testGetString() {
		Assert.assertEquals("UTestDate", Configuration
				.getString(Configuration.PROPERTY_BUILD_DATE_DARK_OBJECTS));
		Assert.assertEquals("UTest.0.1", Configuration
				.getString(Configuration.PROPERTY_BUILD_VERSION_DARK_OBJECTS));

		Assert.assertEquals("WatchDate", Configuration
				.getString(WatchConfig.PROPERTY_BUILD_DATE_DARK_WATCH));
		Assert.assertEquals("Watch.0.1", Configuration
				.getString(WatchConfig.PROPERTY_BUILD_VERSION_DARK_WATCH));
	}

	@Test
	public void testGetVersionString() {
		final String output = WatchConfig.getVersionString();

		Assert.assertTrue(output.contains("---------- Dark Watch ----------"));
		Assert.assertTrue(output.contains("+   Version: Watch.0.1"));
		Assert.assertTrue(output.contains("+   Date: WatchDate"));

		Assert.assertTrue(output.contains("---------- Dark Objects ----------"));
		Assert.assertTrue(output.contains("+   Version: UTest.0.1"));
		Assert.assertTrue(output.contains("+   Date: UTestDate"));

	}

}
