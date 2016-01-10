package net.darkowl.darkNet.darkObjects.devices;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class BaseRestDeviceTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void constructorTest() {
		BaseRestDevice device = new BaseRestDevice() {
			
			@Override
			public void execute(JobExecutionContext arg0) throws JobExecutionException {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public Class<? extends Job> getThisClass() {
				// TODO Auto-generated method stub
				return null;
			}
		};
		
		assertEquals("Unknown",device.getDeviceName());
		
	}

}
