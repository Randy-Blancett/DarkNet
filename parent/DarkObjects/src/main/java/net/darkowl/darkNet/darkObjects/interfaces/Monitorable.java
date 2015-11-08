package net.darkowl.darkNet.darkObjects.interfaces;

import org.quartz.Job;

public interface Monitorable extends Job, DarkDevice {
	Class<? extends Job> getThisClass();

}
