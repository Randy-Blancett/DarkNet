package net.darkowl.darkNet.darkWatch;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;

import net.darkowl.darkNet.darkObjects.config.Configuration;
import net.darkowl.darkNet.darkObjects.devices.RadioThermostat;
import net.darkowl.darkNet.darkObjects.interfaces.DarkDevice;
import net.darkowl.darkNet.darkWatch.config.WatchConfig;
import net.darkowl.darkNet.darkWatch.exceptions.DarkWatchException;

import org.apache.commons.cli.ParseException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DarkWatchTest {
	private PrintStream origOutput;
	private ByteArrayOutputStream myOut = null;

	@Before
	public void setUp() {
//		myOut = new ByteArrayOutputStream();
//		origOutput = System.out;
//		System.setOut(new PrintStream(myOut));
		DarkWatch.kill();
	}

	@After
	public void teardown() throws IOException {
//		System.setOut(origOutput);
//		myOut.close();
//		myOut = null;
	}

	@Test
	public void testMainHelp() {
		String[] params = { CommandLineOptons.HELP.getComandLineFlag() };
		DarkWatch.main(params);
//		assertTrue(myOut.toString().contains("-h,--help"));
	}

	@Test
	public void testMainVersion() {
		String[] params = { CommandLineOptons.VERSION.getComandLineFlag() };
		DarkWatch.main(params);
//		assertTrue(myOut.toString()
//				.contains("---------- Dark Watch ----------"));
	}

	@Test
	public void testMainConfig() {
		WatchConfig.clearProperties();
		String[] params = {
				CommandLineOptons.CONFIG.getComandLineFlag(),
				DarkWatchTest.class.getResource(
						"/DarkWatchTestProperties.properties").getPath() };
		DarkWatch.main(params);
		assertEquals("hello", Configuration.getString("hell.o"));
	}

	@Test
	public void testMainXmlConfig() throws IOException, ParseException,
			DarkWatchException {
		boolean hasError = false;
		// Test that if it is a null xml path we are good
		System.setProperty(WatchConfig.PROPERTY_XML_FILE_LOCATION, "");
		WatchConfig.clearProperties();
		try {
			new DarkWatch(null);

		} catch (DarkWatchException e) {
			hasError = true;
			assertEquals("You are trying to load a blank xml path",
					e.getMessage());
		}

		assertTrue(hasError);
		hasError = false;

		// Test if a file doesnt Exist
		System.setProperty(WatchConfig.PROPERTY_XML_FILE_LOCATION, "badXML");
		WatchConfig.clearProperties();
		try {
			new DarkWatch(null);

		} catch (DarkWatchException e) {
			hasError = true;
			assertEquals(
					"You are trying to load the xml config from: badXML that file location does not exist.",
					e.getMessage());
		}

		assertTrue(hasError);
		hasError = false;

		// Test if the file causes Parse Errors
		String badXmlPath = DarkWatchTest.class.getResource(
				"/xml/TestXMLConfig_Bad.xml").getPath();
		System.setProperty(WatchConfig.PROPERTY_XML_FILE_LOCATION, badXmlPath);
		WatchConfig.clearProperties();
		try {
			new DarkWatch(null);

		} catch (DarkWatchException e) {
			hasError = true;
			assertEquals("You are trying to load the xml config from: "
					+ badXmlPath + " that File is not parseable.",
					e.getMessage());
		}

		assertTrue(hasError);
		hasError = false;

		// Test with a good xml file

		String goodXmlPath = DarkWatchTest.class.getResource(
				"/xml/TestXMLConfig_Good.xml").getPath();
		System.setProperty(WatchConfig.PROPERTY_XML_FILE_LOCATION, goodXmlPath);
		WatchConfig.clearProperties();
		assertNotNull(new DarkWatch(null).getNetConfig());
	}

	@Test
	public void testGetInstance() throws InstantiationException,
			IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException,
			SecurityException, ClassNotFoundException, DarkWatchException {
		boolean hasError = false;
		try {
			DarkWatch.getInstance("", "TestBad");
		} catch (DarkWatchException e) {
			hasError = true;
			assertEquals("Class name can not be null or an empty string.",
					e.getMessage());
		}
		assertTrue(hasError);
		hasError = false;
		try {
			DarkWatch.getInstance(null, "TestBad");
		} catch (DarkWatchException e) {
			hasError = true;
			assertEquals("Class name can not be null or an empty string.",
					e.getMessage());
		}
		assertTrue(hasError);
		hasError = false;
		try {
			DarkWatch.getInstance(
					"net.darkowl.darkNet.darkWatch.config.WatchConfig",
					"TestWatchConfig");
		} catch (DarkWatchException e) {
			hasError = true;
			assertEquals("Failed to create DarkDevice: ", e.getMessage());
		}
		assertTrue(hasError);
		hasError = false;

		try {
			DarkWatch.getInstance("BadClass", "TestBadClass");
		} catch (DarkWatchException e) {
			hasError = true;
			assertEquals(
					"Class: BadClass is not a valid class (can not be found)",
					e.getMessage());
		}
		assertTrue(hasError);
		hasError = false;

		DarkDevice obj = DarkWatch.getInstance(
				"net.darkowl.darkNet.darkObjects.devices.RadioThermostat",
				"TestRadioThermostat");

		assertTrue(obj instanceof DarkDevice);
		assertTrue(obj instanceof RadioThermostat);
		assertEquals("TestRadioThermostat", obj.getDeviceName());

	}
}
