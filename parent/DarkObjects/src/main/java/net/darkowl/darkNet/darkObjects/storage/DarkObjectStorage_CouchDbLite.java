/**
 * 
 */
package net.darkowl.darkNet.darkObjects.storage;

import java.util.Map;

import net.darkowl.darkNet.darkObjects.interfaces.DarkDataStorage;

import org.lightcouch.CouchDbClient;

/**
 * @author Randy Blancett
 * @since Dec 10, 2015
 * 
 */
public class DarkObjectStorage_CouchDbLite extends DarkObjectStorage {
	final CouchDbClient client;

	public DarkObjectStorage_CouchDbLite() {
		client = new CouchDbClient(DarkDataStorage.PROP_DB_NAME, true, "http",
				"127.0.0.1", 5984, "darkowl", "secret");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.darkowl.darkNet.darkObjects.storage.DarkObjectStorage#storeItem(java
	 * .util.Map)
	 */
	@Override
	public void storeItem(Map<String, String> item) {
		client.save(item);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.darkowl.darkNet.darkObjects.storage.DarkObjectStorage#closeConnection
	 * ()
	 */
	@Override
	public void closeConnection() {
		client.shutdown();
	}

}
