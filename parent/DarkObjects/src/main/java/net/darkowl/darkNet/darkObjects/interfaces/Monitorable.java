package net.darkowl.darkNet.darkObjects.interfaces;

import org.quartz.Job;

public interface Monitorable extends Job, DarkDevice {
	/**
	 * This property will hold how many seconds between executions
	 */
	public static final String PROPERTY_INTERVAL_SECONDS = "repeat seconds";

	Class<? extends Job> getThisClass();

}
