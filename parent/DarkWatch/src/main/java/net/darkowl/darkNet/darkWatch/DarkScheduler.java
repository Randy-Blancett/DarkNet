package net.darkowl.darkNet.darkWatch;

import java.util.Map;

import net.darkowl.darkNet.darkObjects.interfaces.Monitorable;
import net.darkowl.darkNet.darkObjects.xml.config.ConfigurationPropertyKeys;
import net.darkowl.darkNet.darkWatch.exceptions.DarkWatchException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class DarkScheduler {
	/**
	 * This is the default number of seconds between runing the scheduled task
	 */
	public final static int DEFAULT_SECOND_INTERVAL = 60 * 5;
	private final static Logger LOGGER = LogManager
			.getLogger(DarkScheduler.class);
	private static Scheduler scheduler;

	protected synchronized static Scheduler getScheduler()
			throws SchedulerException {
		if (DarkScheduler.scheduler == null) {
			DarkScheduler.scheduler = new StdSchedulerFactory().getScheduler();
			DarkScheduler.scheduler.start();
		}
		return DarkScheduler.scheduler;
	}

	/**
	 * Schedule a device monitor to run
	 * 
	 * @since Nov 27, 2015
	 * @param device
	 *            the device
	 * @param properties
	 *            a map of properties
	 */
	public static void schedule(Monitorable device,
			Map<String, String> properties) {
		Scheduler sched;

		try {
			sched = DarkScheduler.getScheduler();
		} catch (final SchedulerException e) {
			DarkScheduler.LOGGER.error("Failed to get Scheduler", e);
			return;
		}

		final JobDetail job = JobBuilder.newJob(device.getThisClass())
				.withIdentity(device.getDeviceName() + "Job", "Monitored")
				.build();

		if (properties != null) {
			job.getJobDataMap().putAll(properties);
		}
		Integer repeatInterval = null;

		if (properties != null
				&& properties
						.containsKey(ConfigurationPropertyKeys.INTERVAL_SECONDS
								.getKey())) {
			repeatInterval = Integer.parseInt(properties
					.get(ConfigurationPropertyKeys.INTERVAL_SECONDS.getKey()));
		}

		if (repeatInterval == null) {
			repeatInterval = DarkScheduler.DEFAULT_SECOND_INTERVAL;
		}

		final Trigger trigger = TriggerBuilder
				.newTrigger()
				.withIdentity(device.getDeviceName() + "Trigger", "Monitored")
				.withSchedule(
						SimpleScheduleBuilder
								.repeatSecondlyForever(repeatInterval)).build();

		try {
			sched.scheduleJob(job, trigger);
			System.out.println("I have scheduled " + job.toString() + " - "
					+ trigger);
		} catch (final SchedulerException e) {
			DarkScheduler.LOGGER.error("Failed to schedule job", e);
			return;
		}

	}

	/**
	 * This will shutdown the scheduler
	 * 
	 * @throws DarkWatchException
	 * 
	 * @since Nov 11, 2015
	 */
	public static void shutdown() throws DarkWatchException {
		try {
			DarkScheduler.getScheduler().shutdown();
			DarkScheduler.scheduler = null;
		} catch (final SchedulerException e) {
			throw new DarkWatchException("Failed to Shutdown Scheduler", e);
		}
	}

	/**
	 * Default constructor
	 * 
	 * @since Nov 27, 2015
	 * @throws SchedulerException
	 */
	public DarkScheduler() throws SchedulerException {
	}

}
