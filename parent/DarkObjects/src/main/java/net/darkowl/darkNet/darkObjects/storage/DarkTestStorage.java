/**
 * 
 */
package net.darkowl.darkNet.darkObjects.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.darkowl.darkNet.darkObjects.interfaces.DarkDataStorage;

/**
 * @author Randy Blancett
 * @since Dec 8, 2015
 * 
 */
public class DarkTestStorage implements DarkDataStorage {
	List<Map<String, String>> storage = new ArrayList<Map<String, String>>();

	@Override
	public void storeItem(Map<String, String> item) {
		this.storage.add(item);
	}

	/**
	 * Clear all data in the store
	 * 
	 * @since Dec 9, 2015
	 */
	public void clear() {
		storage = new ArrayList<Map<String, String>>();
	}

	/**
	 * Get the size of the storage
	 * 
	 * @since Dec 9, 2015
	 * @return
	 */
	public int getSize() {
		return storage.size();
	}

	@Override
	public void closeConnection() {
		clear();
	}
}
