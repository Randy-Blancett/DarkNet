package net.darkowl.darkNet.darkWatch;

/**
 * Enum to hold all the Command Line Options
 * 
 * @author Randy Blancett
 * @since Oct 30, 2015
 * 
 */
public enum CommandLineOptons {
	HELP("h", "help", false, "Shows Help Menu");
	private final String id;
	private final String full;
	private final String description;
	private final boolean arg;

	/**
	 * @since Oct 30, 2015
	 * @param flag
	 * @param fullFlag
	 * @param hasArg
	 * @param desc
	 */
	CommandLineOptons(String flag, String fullFlag, boolean hasArg, String desc) {
		id = flag;
		full = fullFlag;
		description = desc;
		arg = hasArg;
	}

	/**
	 * The Flag
	 * 
	 * @since Oct 30, 2015
	 * @return
	 */
	public String getFlag() {
		return id;
	}

	/**
	 * the description of the flag
	 * 
	 * @since Oct 30, 2015
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Returns if the flag expects an arguement
	 * 
	 * @since Oct 30, 2015
	 * @return the arg
	 */
	public boolean hasArg() {
		return arg;
	}

	/**
	 * @since Oct 30, 2015
	 * @return the full
	 */
	public String getFull() {
		return full;
	}

	/**
	 * returns the flag as it would be seen on the command line (adds the -)
	 * 
	 * @since Oct 30, 2015
	 * @return
	 */
	public String getComandLineFlag() {
		return "-" + id;
	}

}
