package net.darkowl.darnNet.darkObjects.json.radioThermostat;

/**
 * This is the json return form a radio thermostat tstat
 * 
 * @author Randy Blancett
 * @since Dec 3, 2015
 * 
 */
public class TStat {
	private double temp;
	private int tmode;
	private int fmode;
	private int override;
	private int hold;
	private double t_heat;
	private int tstate;
	private int fstate;
	private Time time;
	private int t_type_post;

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("tstat [temp=");
		builder.append(temp);
		builder.append(", tmode=");
		builder.append(tmode);
		builder.append(", fmode=");
		builder.append(fmode);
		builder.append(", override=");
		builder.append(override);
		builder.append(", hold=");
		builder.append(hold);
		builder.append(", t_heat=");
		builder.append(t_heat);
		builder.append(", tstate=");
		builder.append(tstate);
		builder.append(", fstate=");
		builder.append(fstate);
		builder.append(", time=");
		builder.append(time);
		builder.append(", t_type_post=");
		builder.append(t_type_post);
		builder.append("]");
		return builder.toString();
	}

	/**
	 * @since Dec 3, 2015
	 * @return the temp
	 */
	public double getTemp() {
		return temp;
	}

	/**
	 * @since Dec 3, 2015
	 * @param temp
	 *            the temp to set
	 */
	public void setTemp(double temp) {
		this.temp = temp;
	}

	/**
	 * @since Dec 3, 2015
	 * @return the tmode
	 */
	public int getTmode() {
		return tmode;
	}

	/**
	 * @since Dec 3, 2015
	 * @param tmode
	 *            the tmode to set
	 */
	public void setTmode(int tmode) {
		this.tmode = tmode;
	}

	/**
	 * @since Dec 3, 2015
	 * @return the override
	 */
	public int getOverride() {
		return override;
	}

	/**
	 * @since Dec 3, 2015
	 * @param override
	 *            the override to set
	 */
	public void setOverride(int override) {
		this.override = override;
	}

	/**
	 * @since Dec 3, 2015
	 * @return the hold
	 */
	public int getHold() {
		return hold;
	}

	/**
	 * @since Dec 3, 2015
	 * @param hold
	 *            the hold to set
	 */
	public void setHold(int hold) {
		this.hold = hold;
	}

	/**
	 * @since Dec 3, 2015
	 * @return the t_heat
	 */
	public double getT_heat() {
		return t_heat;
	}

	/**
	 * @since Dec 3, 2015
	 * @param t_heat
	 *            the t_heat to set
	 */
	public void setT_heat(double t_heat) {
		this.t_heat = t_heat;
	}

	/**
	 * @since Dec 3, 2015
	 * @return the tstate
	 */
	public int getTstate() {
		return tstate;
	}

	/**
	 * @since Dec 3, 2015
	 * @param tstate
	 *            the tstate to set
	 */
	public void setTstate(int tstate) {
		this.tstate = tstate;
	}

	/**
	 * @since Dec 3, 2015
	 * @return the fstate
	 */
	public int getFstate() {
		return fstate;
	}

	/**
	 * @since Dec 3, 2015
	 * @param fstate
	 *            the fstate to set
	 */
	public void setFstate(int fstate) {
		this.fstate = fstate;
	}

	/**
	 * @since Dec 3, 2015
	 * @return the t_type_post
	 */
	public int getT_type_post() {
		return t_type_post;
	}

	/**
	 * @since Dec 3, 2015
	 * @param t_type_post
	 *            the t_type_post to set
	 */
	public void setT_type_post(int t_type_post) {
		this.t_type_post = t_type_post;
	}

	/**
	 * @since Dec 3, 2015
	 * @return the time
	 */
	public Time getTime() {
		return time;
	}

	/**
	 * @since Dec 3, 2015
	 * @param time
	 *            the time to set
	 */
	public void setTime(Time time) {
		this.time = time;
	}

	/**
	 * @since Dec 3, 2015
	 * @return the fmode
	 */
	public int getFmode() {
		return fmode;
	}

	/**
	 * @since Dec 3, 2015
	 * @param fmode
	 *            the fmode to set
	 */
	public void setFmode(int fmode) {
		this.fmode = fmode;
	}
}
