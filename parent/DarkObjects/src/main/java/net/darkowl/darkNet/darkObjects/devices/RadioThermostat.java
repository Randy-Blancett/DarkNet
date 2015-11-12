package net.darkowl.darkNet.darkObjects.devices;

import net.darkowl.darkNet.darkObjects.interfaces.Monitorable;

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
public class RadioThermostat extends BaseDarkDevice implements Monitorable {
	// public class RadioThermostat implements Monitorable {
	private final static Logger LOGGER = LogManager
			.getLogger(RadioThermostat.class);

	/**
	 * Construct the device with a name
	 * 
	 * @since Nov 6, 2015
	 * @param deviceName
	 *            Name that identifies the device
	 */
	public RadioThermostat(String deviceName) {
		super(deviceName);
	}

	public RadioThermostat() {
		super();
	}

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		LOGGER.info("I am Logging an execution!!!");
	}

	@Override
	public Class<? extends Job> getThisClass() {
		return RadioThermostat.class;
	}

}
