package net.darkowl.darkNet.darkObjects.interfaces;

import java.util.Map;

public interface DarkDataStorage {
	public static String PROP_DB_NAME = "dark_net_objects";

	void storeItem(Map<String, String> item);

	void closeConnection();
}
