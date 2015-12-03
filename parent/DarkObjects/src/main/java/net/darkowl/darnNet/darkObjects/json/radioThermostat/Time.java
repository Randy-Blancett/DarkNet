package net.darkowl.darnNet.darkObjects.json.radioThermostat;

/**
 * Radio thermostat to hold time
 * 
 * @author Randy Blancett
 * @since Dec 3, 2015
 * 
 */
public class Time {
	private int day;
	private int hour;
	private int minute;

	/**
	 * @since Dec 3, 2015
	 * @return the day
	 */
	public int getDay() {
		return day;
	}

	public Time() {
		super();
	}

	public Time(int day, int hour, int minute) {
		super();
		this.day = day;
		this.hour = hour;
		this.minute = minute;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Time [day=");
		builder.append(day);
		builder.append(", hour=");
		builder.append(hour);
		builder.append(", minute=");
		builder.append(minute);
		builder.append("]");
		return builder.toString();
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
	 * @return the hour
	 */
	public int getHour() {
		return hour;
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
	 * @return the minute
	 */
	public int getMinute() {
		return minute;
	}

	/**
	 * @since Dec 3, 2015
	 * @param minute
	 *            the minute to set
	 */
	public void setMinute(int minute) {
		this.minute = minute;
	}
}
