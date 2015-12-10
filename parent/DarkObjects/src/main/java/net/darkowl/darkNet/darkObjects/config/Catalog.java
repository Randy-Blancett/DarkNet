/**
 * 
 */
package net.darkowl.darkNet.darkObjects.config;

import net.darkowl.darkNet.darkObjects.interfaces.DarkDataStorage;
import net.darkowl.darkNet.darkObjects.storage.DarkObjectStorage_CouchDbLite;

/**
 * @author Randy Blancett
 * @since Dec 7, 2015
 * 
 */
public class Catalog {
	private static DarkDataStorage storageAPI = null;

	/**
	 * This will get a Storage api
	 * 
	 * @since Dec 7, 2015
	 * @return storage api
	 */
	public static DarkDataStorage getStorageAPI() {
		if (Catalog.storageAPI == null) {
			Catalog.storageAPI = new DarkObjectStorage_CouchDbLite();
		}
		return Catalog.storageAPI;
	}

	/**
	 * Set the storage api object (normally not needed it will create a
	 * DarkWatchStorage by default)
	 * 
	 * @since Dec 7, 2015
	 * @param storage
	 */
	public static void setStorageAPI(DarkDataStorage storage) {
		Catalog.storageAPI = storage;
	}
}
