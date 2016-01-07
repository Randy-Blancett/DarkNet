package net.darkowl.darkNet.darkObjects.json.radioThermostat;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Map;

import net.darkowl.darkNet.darkObjects.config.Configuration;
import net.darkowl.darnNet.darkObjects.json.radioThermostat.Time;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;

public class TimeTest {
	private final static Logger LOGGER = LogManager
			.getLogger(Configuration.class);

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetterSetters() {
		Time time = new Time();
		time.setDay(1);
		time.setHour(2);
		time.setMinute(23);
		assertEquals(1, time.getDay());
		assertEquals(2, time.getHour());
		assertEquals(23, time.getMinute());
	}

	@Test
	public void testFromJson() throws FileNotFoundException {
		Gson gson = new Gson();
		LOGGER.info("Trying file at: "
				+ RadioThermostatTestJson.TIME_1.getFilename());
		BufferedReader br = new BufferedReader(new FileReader(
				RadioThermostatTestJson.TIME_1.getFilename()));

		// convert the json string back to object
		Time obj = gson.fromJson(br, Time.class);
		assertEquals(3, obj.getDay());
		assertEquals(17, obj.getHour());
		assertEquals(45, obj.getMinute());
	}

	@Test
	public void testToMap() {
		Time time = new Time();
		time.setDay(1);
		time.setHour(2);
		time.setMinute(23);
		Map<String, String> map = time.toMap();

		assertEquals("1", map.get(Time.COL_DAY));
		assertEquals("2", map.get(Time.COL_HOUR));
		assertEquals("23", map.get(Time.COL_MIN));
		assertEquals(Time.class.getName(), map.get(Time.COL_TYPE));

		Time rebuild = new Time(map);
		assertEquals(1, rebuild.getDay());
		assertEquals(2, rebuild.getHour());
		assertEquals(23, rebuild.getMinute());
	}

	@Test
	public void testHasChanged() {
		Time time1 = new Time(1, 4, 34);

		assertFalse(time1.hasChanged(new Time(1, 4, 34)));
		assertTrue(time1.hasChanged(new Time(2, 4, 34)));
		assertTrue(time1.hasChanged(new Time(1, 6, 34)));
		assertTrue(time1.hasChanged(new Time(1, 4, 35)));
	}
}
