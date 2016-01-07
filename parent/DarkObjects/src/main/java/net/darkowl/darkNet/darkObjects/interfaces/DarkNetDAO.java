package net.darkowl.darkNet.darkObjects.interfaces;

import java.util.Map;

public interface DarkNetDAO {
	public static String COL_DATE_TIME = "DATE_TIME";
	public static String COL_TYPE = "TYPE";

	/**
	 * Convert the object to a Map
	 * 
	 * @since Dec 7, 2015
	 * @return
	 */
	Map<String, String> toMap();

	/**
	 * This will return true if the object has changed in a meaningful way
	 * 
	 * @since Dec 26, 2015
	 * @param newObj
	 *            the new Object to see if it has changed
	 */
	boolean hasChanged(Object newObj);

}
