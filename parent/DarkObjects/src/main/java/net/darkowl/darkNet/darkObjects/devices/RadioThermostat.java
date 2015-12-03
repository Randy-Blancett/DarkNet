package net.darkowl.darkNet.darkObjects.devices;

import java.util.List;

import net.darkowl.darkNet.darkObjects.xml.config.Configuration;
import net.darkowl.darnNet.darkObjects.json.radioThermostat.TStat;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * This is the parent object that all RadioThermostats will be built off of. New
 * Instances will only be created when needed. This class will be created to
 * handle the CT50e
 * 
 * @author Randy Blancett
 * @since Oct 28, 2015
 * 
 */
public class RadioThermostat extends BaseJsonRestDevice {

	private final static Logger LOGGER = LogManager
			.getLogger(RadioThermostat.class);

	/**
	 * This is the endpoint that will return general Thermostat Data
	 */
	public final static String ENDPOINT_THERMOSTAT_DATA = "tstat";

	/**
	 * Construct the device with a name
	 * 
	 * @since Nov 6, 2015
	 * @param deviceName
	 *            Name that identifies the device
	 * @param configs
	 *            Set of configurations for the device
	 */
	public RadioThermostat(String deviceName, List<Configuration> configs) {
		super(deviceName, configs);
	}

	/**
	 * This will create an object with no device name this is needed so we can
	 * use Quartz
	 * 
	 * @since Nov 26, 2015
	 */
	public RadioThermostat() {
		super();
	}

	/**
	 * This method will query the device and return information about it's state
	 * 
	 * @since Nov 26, 2015
	 */
	protected void getDeviceInfo() {
		System.out.println(getJson(ENDPOINT_THERMOSTAT_DATA, TStat.class));
	}

	@Override
	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		reloadProperties(context.getJobDetail().getJobDataMap().getWrappedMap());
		getDeviceInfo();
	}

	@Override
	public Class<? extends Job> getThisClass() {
		return RadioThermostat.class;
	}

}
