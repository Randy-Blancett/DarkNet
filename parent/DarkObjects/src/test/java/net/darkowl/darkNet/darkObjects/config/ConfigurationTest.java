package net.darkowl.darkNet.darkObjects.config;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ConfigurationTest {

	@Test
	public void loadConfigFileTest() throws FileNotFoundException, IOException {
		Configuration.loadConfigFile(null);
		Configuration.loadConfigFile("");
		Configuration.loadConfigFile("IAmBad");

		final URL testProp = ConfigurationTest.class
				.getResource("/testProps.properties");

		Configuration.loadConfigFile(testProp.getPath());
		Assert.assertEquals("Hello", Configuration.getString("test1"));
		Assert.assertEquals("Good Bye", Configuration.getString("test2"));
	}

	@Before
	public void setUp() {
		try {
			Configuration.init();
		} catch (final IOException e) {
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
		Assert.assertEquals("UTestDate", Configuration
				.getString(Configuration.PROPERTY_BUILD_DATE_DARK_OBJECTS));
		Assert.assertEquals("UTest.0.1", Configuration
				.getString(Configuration.PROPERTY_BUILD_VERSION_DARK_OBJECTS));
		Assert.assertTrue(Configuration.loaded);
	}

	@Test
	public void testGetString() {
		Assert.assertEquals("UTestDate", Configuration
				.getString(Configuration.PROPERTY_BUILD_DATE_DARK_OBJECTS));
		Assert.assertEquals("UTest.0.1", Configuration
				.getString(Configuration.PROPERTY_BUILD_VERSION_DARK_OBJECTS));

	}

	@Test
	public void testGetVersionString() {
		final String output = Configuration.getVersionString();

		Assert.assertTrue(output.contains("---------- Dark Objects ----------"));
		Assert.assertTrue(output.contains("+   Version: UTest.0.1"));
		Assert.assertTrue(output.contains("+   Date: UTestDate"));

	}

	@Test
	public void testLoadBuildProperties() throws IOException {
		final String oldPropLocation = Configuration
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
}
