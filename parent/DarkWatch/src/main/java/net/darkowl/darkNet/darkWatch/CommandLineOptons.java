package net.darkowl.darkNet.darkWatch;

/**
 * Enum to hold all the Command Line Options
 * 
 * @author Randy Blancett
 * @since Oct 30, 2015
 * 
 */
public enum CommandLineOptons {
	CONFIG("c", "config", true, "Location of the configuration properties file"),

	HELP("h", "help", false, "Shows Help Menu"),

	VERSION("v", "version", false, "Shows Version Information");

	private final boolean arg;
	private final String description;
	private final String full;
	private final String id;

	/**
	 * @since Oct 30, 2015
	 * @param flag
	 *            Command line Flag
	 * @param fullFlag
	 *            Long hand option flag
	 * @param hasArg
	 *            True if it expects arguments
	 * @param desc
	 *            Descripton of the flag
	 */
	CommandLineOptons(String flag, String fullFlag, boolean hasArg, String desc) {
		this.id = flag;
		this.full = fullFlag;
		this.description = desc;
		this.arg = hasArg;
	}

	/**
	 * returns the flag as it would be seen on the command line (adds the -)
	 * 
	 * @since Oct 30, 2015
	 * @return Get the command Line Argument
	 */
	public String getComandLineFlag() {
		return "-" + this.id;
	}

	/**
	 * the description of the flag
	 * 
	 * @since Oct 30, 2015
	 * @return the description
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * The Flag
	 * 
	 * @since Oct 30, 2015
	 * @return return the Flag id
	 */
	public String getFlag() {
		return this.id;
	}

	/**
	 * @since Oct 30, 2015
	 * @return the full
	 */
	public String getFull() {
		return this.full;
	}

	/**
	 * Returns if the flag expects an arguement
	 * 
	 * @since Oct 30, 2015
	 * @return the arg
	 */
	public boolean hasArg() {
		return this.arg;
	}

}
