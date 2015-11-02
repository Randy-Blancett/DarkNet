package net.darkowl.darkNet.darkObjects.config;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ConfigurationTest {

	@Before
	public void setUp() {
		try {
			Configuration.init();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@After
	public void tearDown() {
	}

	@Test
	public void test() {
		assertEquals("UTestDate",
				Configuration.getString(Configuration.BUILD_DATE_DARK_OBJECTS));
		assertEquals("UTest.0.1",
				Configuration
						.getString(Configuration.BUILD_VERSION_DARK_OBJECTS));
		assertTrue(Configuration.loaded);
	}

	@Test
	public void testGetString() {
		assertEquals("UTestDate",
				Configuration.getString(Configuration.BUILD_DATE_DARK_OBJECTS));
		assertEquals("UTest.0.1",
				Configuration
						.getString(Configuration.BUILD_VERSION_DARK_OBJECTS));

	}

	@Test
	public void testGetVersionString() {
		String output = Configuration.getVersionString();

		assertTrue(output.contains("---------- Dark Objects ----------"));
		assertTrue(output.contains("+   Version: UTest.0.1"));
		assertTrue(output.contains("+   Date: UTestDate"));

	}
}
