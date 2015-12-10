package net.darkowl.darkNet.darkObjects.interfaces;

import java.util.Map;

public interface DarkNetDAO {
	public static String COL_TYPE = "TYPE";

	/**
	 * Convert the object to a Map
	 * 
	 * @since Dec 7, 2015
	 * @return
	 */
	Map<String, String> toMap();

}
