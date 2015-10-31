/**
 * 
 */
package net.darkowl.darkNet.darkWatch;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author Randy Blancett
 * @since Oct 30, 2015
 * 
 */
public class DarkWatch {
	private static Options options = new Options();
	private final static Logger LOGGER = LogManager.getLogger(DarkWatch.class);

	static {
		for (CommandLineOptons option : CommandLineOptons.values()) {
			options.addOption(option.getFlag(), option.getFull(),
					option.hasArg(), option.getDescription());
		}
	}

	private static void printHelp() {
		HelpFormatter formatter = new HelpFormatter();
		formatter.printHelp("darkWatch", options);
	}

	/**
	 * @since Oct 30, 2015
	 * @param args
	 */
	public DarkWatch(String[] args) {
		CommandLineParser parser = new DefaultParser();
		try {
			CommandLine cmd = parser.parse(options, args);

			if (cmd.hasOption(CommandLineOptons.HELP.getFlag())) {
				printHelp();
				return;
			}
		} catch (ParseException e) {
			LOGGER.error("Failed to parse Command Line Arguments", e);
		}

	}

	/**
	 * @since Oct 30, 2015
	 * @param args
	 */
	public static void main(String[] args) {
		LOGGER.info("I am watching you...");
		DarkWatch me = new DarkWatch(args);
		LOGGER.info("... You are no longer being watched.");
	}
}
