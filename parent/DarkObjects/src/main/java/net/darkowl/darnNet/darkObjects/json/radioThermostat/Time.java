package net.darkowl.darnNet.darkObjects.json.radioThermostat;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import net.darkowl.darkNet.darkObjects.Watched;
import net.darkowl.darkNet.darkObjects.interfaces.AbstractDarkNetDAO;
import net.darkowl.darkNet.darkObjects.interfaces.DarkNetDAO;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Radio thermostat to hold time
 * 
 * @author Randy Blancett
 * @since Dec 3, 2015
 * 
 */
public class Time extends AbstractDarkNetDAO {

	private final static Logger LOGGER = LogManager.getLogger(Time.class);
	public static String COL_DAY = "DAY";
	public static String COL_HOUR = "HOUR";
	public static String COL_MIN = "MIN";

	private int day;
	private int hour;
	private int minute;

	public Time() {
		super();
	}

	public Time(int day, int hour, int minute) {
		super();
		this.day = day;
		this.hour = hour;
		this.minute = minute;
	}

	public Time(Map<String, String> data) {
		super();
		this.day = Integer.parseInt(data.get(Time.COL_DAY));
		this.hour = Integer.parseInt(data.get(Time.COL_HOUR));
		this.minute = Integer.parseInt(data.get(Time.COL_MIN));
	}

	/**
	 * @since Dec 3, 2015
	 * @return the day
	 */
	@Watched
	public int getDay() {
		return this.day;
	}

	/**
	 * @since Dec 3, 2015
	 * @return the hour
	 */
	@Watched
	public int getHour() {
		return this.hour;
	}

	/**
	 * @since Dec 3, 2015
	 * @return the minute
	 */
	@Watched
	public int getMinute() {
		return this.minute;
	}

	/**
	 * @since Dec 3, 2015
	 * @param day
	 *            the day to set
	 */
	public void setDay(int day) {
		this.day = day;
	}

	/**
	 * @since Dec 3, 2015
	 * @param hour
	 *            the hour to set
	 */
	public void setHour(int hour) {
		this.hour = hour;
	}

	/**
	 * @since Dec 3, 2015
	 * @param minute
	 *            the minute to set
	 */
	public void setMinute(int minute) {
		this.minute = minute;
	}

	@Override
	public Map<String, String> toMap() {
		final Map<String, String> output = new HashMap<String, String>();
		output.put(Time.COL_DAY, Integer.toString(this.getDay()));
		output.put(Time.COL_HOUR, Integer.toString(this.getHour()));
		output.put(Time.COL_MIN, Integer.toString(this.getMinute()));
		output.put(DarkNetDAO.COL_TYPE, Time.class.getName());

		return output;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		builder.append("Time [day=");
		builder.append(this.day);
		builder.append(", hour=");
		builder.append(this.hour);
		builder.append(", minute=");
		builder.append(this.minute);
		builder.append("]");
		return builder.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.darkowl.darkNet.darkObjects.interfaces.DarkNetDAO#hasChanged(java
	 * .lang.Object)
	 */
	@Override
	public boolean hasChanged(Object newObj) {
		if (!(newObj instanceof Time)) {
			LOGGER.error("You are these object have to be of the same type");
			throw new ClassCastException("This object is not of type: "
					+ Time.class.getName());
		}
		Method[] methods = Time.class.getMethods();
		return hasChanged(methods, this, newObj);
	}
}
