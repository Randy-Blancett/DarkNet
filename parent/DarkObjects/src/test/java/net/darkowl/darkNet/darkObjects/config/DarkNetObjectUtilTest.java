package net.darkowl.darkNet.darkObjects.config;

import static org.junit.Assert.*;

import java.lang.reflect.InvocationTargetException;

import net.darkowl.darkNet.darkObjects.interfaces.DarkNetDAO;
import net.darkowl.darkNet.darkObjects.util.DarkNetObjectUtil;
import net.darkowl.darnNet.darkObjects.json.radioThermostat.Time;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DarkNetObjectUtilTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() throws ClassNotFoundException, NoSuchMethodException,
			SecurityException, InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {
		Time testTime1 = new Time(3, 12, 45);
		DarkNetDAO output = DarkNetObjectUtil.form(testTime1.toMap());
		assertTrue(output instanceof DarkNetDAO);
		assertTrue(output instanceof Time);

		assertEquals(testTime1.toMap().get(Time.COL_HOUR),
				output.toMap().get(Time.COL_HOUR));
	}

}
