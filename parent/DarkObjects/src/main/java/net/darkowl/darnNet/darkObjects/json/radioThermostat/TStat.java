package net.darkowl.darnNet.darkObjects.json.radioThermostat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import net.darkowl.darkNet.darkObjects.interfaces.DarkNetDAO;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * This is the json return form a radio thermostat tstat
 * 
 * @author Randy Blancett
 * @since Dec 3, 2015
 * 
 */
public class TStat implements DarkNetDAO {
	public static String COL_F_MODE = "F_MODE";
	public static String COL_F_STATE = "F_STATE";
	public static String COL_HOLD = "HOLD";
	public static String COL_OVERRIDE = "OVERRIDE";
	public static String COL_T_HEAT = "T_HEAT";
	public static String COL_T_MODE = "T_MODE";
	public static String COL_T_STATE = "T_STATE";
	public static String COL_T_TYPE_POST = "T_TYPE_POST";
	public static String COL_TEMP = "TEMP";
	public static String COL_TIME = "TIME";
	public static String COL_DATE_TIME = "DATE_TIME";

	private int fmode;
	private int fstate;
	private int hold;
	private int override;
	private double t_heat;
	private int t_type_post;
	private double temp;
	private Time time;
	private int tmode;
	private int tstate;
	private Date dateTime;

	/**
	 * Get Date the object was created
	 * 
	 * @since Dec 9, 2015
	 * @return
	 */
	public Date getDateTime() {
		return dateTime;
	}

	SimpleDateFormat sdf = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss Z");
	private final static Logger LOGGER = LogManager.getLogger(DarkNetDAO.class);

	public TStat() {
		super();
		dateTime = new Date();
	}

	public TStat(Map<String, String> data) {
		super();

		this.temp = Double.parseDouble(data.get(TStat.COL_TEMP));
		this.tmode = Integer.parseInt(data.get(TStat.COL_T_MODE));
		this.fmode = Integer.parseInt(data.get(TStat.COL_F_MODE));
		this.override = Integer.parseInt(data.get(TStat.COL_OVERRIDE));
		this.hold = Integer.parseInt(data.get(TStat.COL_HOLD));
		this.t_heat = Double.parseDouble(data.get(TStat.COL_T_HEAT));
		this.tstate = Integer.parseInt(data.get(TStat.COL_T_STATE));
		this.fstate = Integer.parseInt(data.get(TStat.COL_F_STATE));
		this.time = new Time(data);
		this.t_type_post = Integer.parseInt(data.get(TStat.COL_T_TYPE_POST));
		try {
			this.dateTime = sdf.parse(data.get(TStat.COL_DATE_TIME));
		} catch (ParseException e) {
			LOGGER.error("Failed to parse Date Time to create object", e);
		}
	}

	/**
	 * @since Dec 3, 2015
	 * @return the fmode
	 */
	public int getFmode() {
		return this.fmode;
	}

	/**
	 * @since Dec 3, 2015
	 * @return the fstate
	 */
	public int getFstate() {
		return this.fstate;
	}

	/**
	 * @since Dec 3, 2015
	 * @return the hold
	 */
	public int getHold() {
		return this.hold;
	}

	/**
	 * @since Dec 3, 2015
	 * @return the override
	 */
	public int getOverride() {
		return this.override;
	}

	/**
	 * @since Dec 3, 2015
	 * @return the t_heat
	 */
	public double getT_heat() {
		return this.t_heat;
	}

	/**
	 * @since Dec 3, 2015
	 * @return the t_type_post
	 */
	public int getT_type_post() {
		return this.t_type_post;
	}

	/**
	 * @since Dec 3, 2015
	 * @return the temp
	 */
	public double getTemp() {
		return this.temp;
	}

	/**
	 * @since Dec 3, 2015
	 * @return the time
	 */
	public Time getTime() {
		return this.time;
	}

	/**
	 * @since Dec 3, 2015
	 * @return the tmode
	 */
	public int getTmode() {
		return this.tmode;
	}

	/**
	 * @since Dec 3, 2015
	 * @return the tstate
	 */
	public int getTstate() {
		return this.tstate;
	}

	/**
	 * @since Dec 3, 2015
	 * @param fmode
	 *            the fmode to set
	 */
	public void setFmode(int fmode) {
		this.fmode = fmode;
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
	 * @param hold
	 *            the hold to set
	 */
	public void setHold(int hold) {
		this.hold = hold;
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
	 * @param t_heat
	 *            the t_heat to set
	 */
	public void setT_heat(double t_heat) {
		this.t_heat = t_heat;
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
	 * @param temp
	 *            the temp to set
	 */
	public void setTemp(double temp) {
		this.temp = temp;
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
	 * @param tmode
	 *            the tmode to set
	 */
	public void setTmode(int tmode) {
		this.tmode = tmode;
	}

	/**
	 * @since Dec 3, 2015
	 * @param tstate
	 *            the tstate to set
	 */
	public void setTstate(int tstate) {
		this.tstate = tstate;
	}

	@Override
	public Map<String, String> toMap() {
		final Map<String, String> output = new HashMap<String, String>();
		output.put(TStat.COL_TEMP, Double.toString(this.getTemp()));
		output.put(TStat.COL_T_MODE, Integer.toString(this.getTmode()));
		output.put(TStat.COL_F_MODE, Integer.toString(this.getFmode()));
		output.put(TStat.COL_OVERRIDE, Integer.toString(this.getOverride()));
		output.put(TStat.COL_HOLD, Integer.toString(this.getHold()));
		output.put(TStat.COL_T_HEAT, Double.toString(this.getT_heat()));
		output.put(TStat.COL_T_STATE, Integer.toString(this.getTstate()));
		output.put(TStat.COL_F_STATE, Integer.toString(this.getFstate()));
		output.putAll(this.getTime().toMap());
		output.put(TStat.COL_T_TYPE_POST,
				Integer.toString(this.getT_type_post()));
		output.put(DarkNetDAO.COL_TYPE, TStat.class.toString());
		output.put(COL_DATE_TIME, sdf.format(dateTime));

		return output;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TStat [fmode=");
		builder.append(fmode);
		builder.append(", fstate=");
		builder.append(fstate);
		builder.append(", hold=");
		builder.append(hold);
		builder.append(", override=");
		builder.append(override);
		builder.append(", t_heat=");
		builder.append(t_heat);
		builder.append(", t_type_post=");
		builder.append(t_type_post);
		builder.append(", temp=");
		builder.append(temp);
		builder.append(", time=");
		builder.append(time);
		builder.append(", tmode=");
		builder.append(tmode);
		builder.append(", tstate=");
		builder.append(tstate);
		builder.append(", dateTime=");
		builder.append(sdf.format(dateTime));
		builder.append("]");
		return builder.toString();
	}
}
