package net.darkowl.darkNet.darkObjects.interfaces;

import java.util.Date;
import java.util.Map;

public interface DarkDataStorage {
	/**
	 * 
	 */
	public static String PROP_DB_NAME = "dark_net_objects";

	/**
	 * Close the connection
	 * 
	 * @since Dec 17, 2015
	 */
	void closeConnection();

	/**
	 * This is a method used for debuging to dump all data in the store
	 * 
	 * @since Dec 17, 2015
	 */
	void dumpData();

	/**
	 * Gets all items of a given type
	 * 
	 * @since Dec 23, 2015
	 * @param type
	 * @return
	 */
	Map<Date, Map<String, String>> getAll(String type);

	/**
	 * This will remove a given data item
	 * 
	 * @since Jan 10, 2016
	 * @param id
	 * @param key
	 */
	void remove(String id, Date key);

	/**
	 * This method is used when you only care about changes it should find the
	 * last item of the same time, check if it is the same, If it is the same
	 * then the old item will be deleted and this will be inserted, if it is
	 * Different the old item will remain and this one will be added
	 * 
	 * @since Dec 23, 2015
	 * @param id
	 *            this is the id of the type of data that is being stored
	 * @param item
	 *            this is a map of values that represents the item
	 */
	public void storeChange(String id, Map<String, String> item);

	/**
	 * Store a map to the backend
	 * 
	 * @since Dec 17, 2015
	 * @param id
	 *            this is the id of the type of data that is being stored
	 * @param item
	 *            this is a map of values that represents the item
	 */
	public void storeItem(String id, Map<String, String> item);
}
