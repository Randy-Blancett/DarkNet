package net.darkowl.darkNet.darkWatch;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import net.darkowl.darkNet.darkObjects.config.Configuration;
import net.darkowl.darkNet.darkObjects.devices.RadioThermostat;
import net.darkowl.darkNet.darkObjects.interfaces.DarkDevice;
import net.darkowl.darkNet.darkWatch.config.WatchConfig;
import net.darkowl.darkNet.darkWatch.exceptions.DarkWatchException;

import org.apache.commons.cli.ParseException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DarkWatchTest {
	@Before
	public void setUp() {
		// myOut = new ByteArrayOutputStream();
		// origOutput = System.out;
		// System.setOut(new PrintStream(myOut));
		DarkWatch.kill();
	}

	@After
	public void teardown() throws IOException {
		// System.setOut(origOutput);
		// myOut.close();
		// myOut = null;
	}

	@Test
	public void testGetInstance() throws InstantiationException,
			IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException,
			SecurityException, ClassNotFoundException, DarkWatchException {
		boolean hasError = false;
		try {
			DarkWatch.getInstance("", "TestBad", null);
		} catch (final DarkWatchException e) {
			hasError = true;
			Assert.assertEquals(
					"Class name can not be null or an empty string.",
					e.getMessage());
		}
		Assert.assertTrue(hasError);
		hasError = false;
		try {
			DarkWatch.getInstance(null, "TestBad", null);
		} catch (final DarkWatchException e) {
			hasError = true;
			Assert.assertEquals(
					"Class name can not be null or an empty string.",
					e.getMessage());
		}
		Assert.assertTrue(hasError);
		hasError = false;
		try {
			DarkWatch.getInstance(
					"net.darkowl.darkNet.darkWatch.config.WatchConfig",
					"TestWatchConfig", null);
		} catch (final DarkWatchException e) {
			hasError = true;
			Assert.assertEquals("Failed to create DarkDevice: ", e.getMessage());
		}
		Assert.assertTrue(hasError);
		hasError = false;

		try {
			DarkWatch.getInstance("BadClass", "TestBadClass", null);
		} catch (final DarkWatchException e) {
			hasError = true;
			Assert.assertEquals(
					"Class: BadClass is not a valid class (can not be found)",
					e.getMessage());
		}
		Assert.assertTrue(hasError);
		hasError = false;

		final DarkDevice obj = DarkWatch.getInstance(
				"net.darkowl.darkNet.darkObjects.devices.RadioThermostat",
				"TestRadioThermostat", null);

		Assert.assertTrue(obj instanceof DarkDevice);
		Assert.assertTrue(obj instanceof RadioThermostat);
		Assert.assertEquals("TestRadioThermostat", obj.getDeviceName());

	}

	@Test
	public void testMainConfig() {
		Configuration.clearProperties();
		final String[] params = {
				CommandLineOptons.CONFIG.getComandLineFlag(),
				DarkWatchTest.class.getResource(
						"/DarkWatchTestProperties.properties").getPath() };
		DarkWatch.main(params);
		Assert.assertEquals("hello", Configuration.getString("hell.o"));
	}

	@Test
	public void testMainHelp() {
		final String[] params = { CommandLineOptons.HELP.getComandLineFlag() };
		DarkWatch.main(params);
		// assertTrue(myOut.toString().contains("-h,--help"));
	}

	@Test
	public void testMainVersion() {
		final String[] params = { CommandLineOptons.VERSION.getComandLineFlag() };
		DarkWatch.main(params);
		// assertTrue(myOut.toString()
		// .contains("---------- Dark Watch ----------"));
	}

	@Test
	public void testMainXmlConfig() throws IOException, ParseException,
			DarkWatchException {
		boolean hasError = false;
		// Test that if it is a null xml path we are good
		System.setProperty(WatchConfig.PROPERTY_XML_FILE_LOCATION, "");
		Configuration.clearProperties();
		try {
			new DarkWatch(null);

		} catch (final DarkWatchException e) {
			hasError = true;
			Assert.assertEquals("You are trying to load a blank xml path",
					e.getMessage());
		}

		Assert.assertTrue(hasError);
		hasError = false;

		// Test if a file doesnt Exist
		System.setProperty(WatchConfig.PROPERTY_XML_FILE_LOCATION, "badXML");
		Configuration.clearProperties();
		try {
			new DarkWatch(null);

		} catch (final DarkWatchException e) {
			hasError = true;
			Assert.assertEquals(
					"You are trying to load the xml config from: badXML that file location does not exist.",
					e.getMessage());
		}

		Assert.assertTrue(hasError);
		hasError = false;

		// Test if the file causes Parse Errors
		final String badXmlPath = DarkWatchTest.class.getResource(
				"/xml/TestXMLConfig_Bad.xml").getPath();
		System.setProperty(WatchConfig.PROPERTY_XML_FILE_LOCATION, badXmlPath);
		Configuration.clearProperties();
		try {
			new DarkWatch(null);

		} catch (final DarkWatchException e) {
			hasError = true;
			Assert.assertEquals("You are trying to load the xml config from: "
					+ badXmlPath + " that File is not parseable.",
					e.getMessage());
		}

		Assert.assertTrue(hasError);
		hasError = false;

		// Test with a good xml file

		final String goodXmlPath = DarkWatchTest.class.getResource(
				"/xml/TestXMLConfig_Good.xml").getPath();
		System.setProperty(WatchConfig.PROPERTY_XML_FILE_LOCATION, goodXmlPath);
		Configuration.clearProperties();
		Assert.assertNotNull(new DarkWatch(null).getNetConfig());
	}
}
