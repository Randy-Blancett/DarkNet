package net.darkowl.darkNet.darkObjects.config;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;

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
		Configuration.clearProperties();
	}

	@Test
	public void test() {
		assertEquals(
				"UTestDate",
				Configuration
						.getString(Configuration.PROPERTY_BUILD_DATE_DARK_OBJECTS));
		assertEquals(
				"UTest.0.1",
				Configuration
						.getString(Configuration.PROPERTY_BUILD_VERSION_DARK_OBJECTS));
		assertTrue(Configuration.loaded);
	}

	@Test
	public void testGetString() {
		assertEquals(
				"UTestDate",
				Configuration
						.getString(Configuration.PROPERTY_BUILD_DATE_DARK_OBJECTS));
		assertEquals(
				"UTest.0.1",
				Configuration
						.getString(Configuration.PROPERTY_BUILD_VERSION_DARK_OBJECTS));

	}

	@Test
	public void testGetVersionString() {
		String output = Configuration.getVersionString();

		assertTrue(output.contains("---------- Dark Objects ----------"));
		assertTrue(output.contains("+   Version: UTest.0.1"));
		assertTrue(output.contains("+   Date: UTestDate"));

	}

	@Test
	public void testLoadBuildProperties() throws IOException {
		String oldPropLocation = Configuration
				.getString(Configuration.PROPERTY_BUILD_FILE_LOCATION_DARK_OBJECTS);

		Configuration.setProp(
				Configuration.PROPERTY_BUILD_FILE_LOCATION_DARK_OBJECTS,
				"/BadName", true);
		Configuration.loadBuildProperties();

		Configuration.setProp(
				Configuration.PROPERTY_BUILD_FILE_LOCATION_DARK_OBJECTS,
				oldPropLocation, true);
		Configuration.loadBuildProperties();
	}

	@Test
	public void loadConfigFileTest() throws FileNotFoundException, IOException {
		Configuration.loadConfigFile(null);
		Configuration.loadConfigFile("");
		Configuration.loadConfigFile("IAmBad");

		URL testProp = ConfigurationTest.class
				.getResource("/testProps.properties");

		Configuration.loadConfigFile(testProp.getPath());
		assertEquals("Hello", Configuration.getString("test1"));
		assertEquals("Good Bye", Configuration.getString("test2"));
	}
}
