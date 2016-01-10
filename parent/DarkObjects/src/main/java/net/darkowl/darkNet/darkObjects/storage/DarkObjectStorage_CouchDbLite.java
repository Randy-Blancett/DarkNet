/**
 * 
 */
package net.darkowl.darkNet.darkObjects.storage;

import java.util.Date;
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
		this.client = new CouchDbClient(DarkDataStorage.PROP_DB_NAME, true,
				"http", "127.0.0.1", 5984, "darkowl", "secret");
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
		this.client.shutdown();
	}

	@Override
	public void dumpData() {
		// TODO Auto-generated method stub

	}

	@Override
	public Map<Date, Map<String, String>> getAll(String type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void storeChange(String id, Map<String, String> item) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.darkowl.darkNet.darkObjects.storage.DarkObjectStorage#storeItem(java
	 * .util.Map)
	 */
	@Override
	public void storeItem(String id, Map<String, String> item) {
		this.client.save(item);
	}

	@Override
	public void remove(String id, Date key) {
		// TODO Auto-generated method stub
		
	}

}
