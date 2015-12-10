package net.darkowl.darkNet.darkObjects.json.radioThermostat;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Map;

import net.darkowl.darkNet.darkObjects.config.Configuration;
import net.darkowl.darnNet.darkObjects.json.radioThermostat.TStat;
import net.darkowl.darnNet.darkObjects.json.radioThermostat.Time;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;

public class TstatTest {
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
		TStat tStat = new TStat();
		Time time = new Time(1, 2, 23);

		tStat.setFmode(1);
		tStat.setFstate(2);
		tStat.setHold(3);
		tStat.setOverride(4);
		tStat.setT_heat(1.2);
		tStat.setT_type_post(5);
		tStat.setTemp(2.3);
		tStat.setTime(time);
		tStat.setTmode(6);
		tStat.setTstate(7);

		assertEquals(1, tStat.getFmode());
		assertEquals(2, tStat.getFstate());
		assertEquals(3, tStat.getHold());
		assertEquals(4, tStat.getOverride());
		assertEquals(1.2, tStat.getT_heat(), 0.01);
		assertEquals(5, tStat.getT_type_post());
		assertEquals(2.3, tStat.getTemp(), 0.01);
		assertEquals(time, tStat.getTime());
		assertEquals(6, tStat.getTmode());
		assertEquals(7, tStat.getTstate());
	}

	@Test
	public void testFromJson() throws FileNotFoundException {
		Gson gson = new Gson();
		Time time = new Time(3, 17, 45);
		LOGGER.info("Trying file at: "
				+ RadioThermostatTestJson.TSTAT_1.getFilename());
		BufferedReader br = new BufferedReader(new FileReader(
				RadioThermostatTestJson.TSTAT_1.getFilename()));

		// convert the json string back to object
		TStat tStat = gson.fromJson(br, TStat.class);

		assertEquals(0, tStat.getFmode());
		assertEquals(0, tStat.getFstate());
		assertEquals(0, tStat.getHold());
		assertEquals(0, tStat.getOverride());
		assertEquals(65.00, tStat.getT_heat(), 0.01);
		assertEquals(0, tStat.getT_type_post());
		assertEquals(65.00, tStat.getTemp(), 0.01);
		assertEquals(time.toString(), tStat.getTime().toString());
		assertEquals(1, tStat.getTmode());
		assertEquals(0, tStat.getTstate());
	}

	@Test
	public void testToString() {
		TStat tStat = new TStat();
		Time time = new Time(1, 2, 23);
		tStat.setFmode(1);
		tStat.setFstate(2);
		tStat.setHold(3);
		tStat.setOverride(4);
		tStat.setT_heat(1.2);
		tStat.setT_type_post(5);
		tStat.setTemp(2.3);
		tStat.setTime(time);
		tStat.setTmode(6);
		tStat.setTstate(7);
		assertTrue(tStat
				.toString()
				.contains(
						"TStat [fmode=1, fstate=2, hold=3, override=4, t_heat=1.2, t_type_post=5, temp=2.3, time=Time [day=1, hour=2, minute=23], tmode=6, tstate=7"));
	}

	@Test
	public void testToMap() throws FileNotFoundException {
		Gson gson = new Gson();
		Time time = new Time(3, 17, 45);
		LOGGER.info("Trying file at: "
				+ RadioThermostatTestJson.TSTAT_1.getFilename());
		BufferedReader br = new BufferedReader(new FileReader(
				RadioThermostatTestJson.TSTAT_1.getFilename()));

		// convert the json string back to object
		TStat tStat = gson.fromJson(br, TStat.class);

		Map<String, String> map = tStat.toMap();
		assertEquals("0", map.get(TStat.COL_F_MODE));
		assertEquals("0", map.get(TStat.COL_F_STATE));
		assertEquals("0", map.get(TStat.COL_HOLD));
		assertEquals("0", map.get(TStat.COL_OVERRIDE));
		assertEquals("65.0", map.get(TStat.COL_T_HEAT));
		assertEquals("0", map.get(TStat.COL_T_TYPE_POST));
		assertEquals("65.0", map.get(TStat.COL_TEMP));
		assertEquals("3", map.get(Time.COL_DAY));
		assertEquals("17", map.get(Time.COL_HOUR));
		assertEquals("45", map.get(Time.COL_MIN));
		assertEquals("1", map.get(TStat.COL_T_MODE));
		assertEquals("0", map.get(TStat.COL_T_STATE));
		Assert.assertNotNull(map.get(TStat.COL_DATE_TIME));

		TStat rebuild = new TStat(map);

		assertEquals(0, rebuild.getFmode());
		assertEquals(0, rebuild.getFstate());
		assertEquals(0, rebuild.getHold());
		assertEquals(0, rebuild.getOverride());
		assertEquals(65.00, rebuild.getT_heat(), 0.01);
		assertEquals(0, rebuild.getT_type_post());
		assertEquals(65.00, rebuild.getTemp(), 0.01);
		assertEquals(time.toString(), rebuild.getTime().toString());
		assertEquals(1, rebuild.getTmode());
		assertEquals(0, rebuild.getTstate());
		assertEquals(tStat.getDateTime().toString(), rebuild.getDateTime()
				.toString());
	}
}
