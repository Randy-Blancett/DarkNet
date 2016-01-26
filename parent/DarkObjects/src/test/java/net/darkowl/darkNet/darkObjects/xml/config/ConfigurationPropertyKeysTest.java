package net.darkowl.darkNet.darkObjects.xml.config;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ConfigurationPropertyKeysTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testConstructor() {
		assertEquals("IP", ConfigurationPropertyKeys.IP.getKey());
		assertEquals("INTERVAL_SECONDS",
				ConfigurationPropertyKeys.INTERVAL_SECONDS.getKey());
	}

}
