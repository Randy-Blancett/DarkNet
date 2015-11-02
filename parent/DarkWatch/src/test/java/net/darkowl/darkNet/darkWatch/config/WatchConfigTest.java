/**
 * 
 */
package net.darkowl.darkNet.darkWatch.config;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

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
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testGetString() {
		assertEquals("UTestDate",
				WatchConfig.getString(WatchConfig.BUILD_DATE_DARK_OBJECTS));
		assertEquals("UTest.0.1",
				WatchConfig.getString(WatchConfig.BUILD_VERSION_DARK_OBJECTS));

		assertEquals("WatchDate",
				WatchConfig.getString(WatchConfig.BUILD_DATE_DARK_WATCH));
		assertEquals("Watch.0.1",
				WatchConfig.getString(WatchConfig.BUILD_VERSION_DARK_WATCH));
	}

	@Test
	public void testGetVersionString() {
		String output = WatchConfig.getVersionString();

		assertTrue(output.contains("---------- Dark Watch ----------"));
		assertTrue(output.contains("+   Version: Watch.0.1"));
		assertTrue(output.contains("+   Date: WatchDate"));

		assertTrue(output.contains("---------- Dark Objects ----------"));
		assertTrue(output.contains("+   Version: UTest.0.1"));
		assertTrue(output.contains("+   Date: UTestDate"));

	}

	
}
