/**
 * 
 */
package net.darkowl.darkNet.darkObjects.storage;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentNavigableMap;

import net.darkowl.darkNet.darkObjects.config.Configuration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mapdb.DB;
import org.mapdb.DBMaker;

/**
 * This object will store data in the Map DB
 * 
 * @author Randy Blancett
 * @since Dec 10, 2015
 * 
 */
public class DarkObjectStorage_MapDb extends DarkObjectStorage {
	private final static Logger LOGGER = LogManager
			.getLogger(DarkObjectStorage_MapDb.class);
	public static String OBJECT_DB = "dark_net_object_db";
	private DB db = null;
	File dbFile = null;
	final String dbFileName = "DarkNetDB.ddb";

	Map<String, ConcurrentNavigableMap<Date, Map<String, String>>> maps = null;

	public DarkObjectStorage_MapDb() {
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
		final DB localDB = this.getDB();
		if (!localDB.isClosed()) {
			localDB.close();
		}
	}

	@Override
	public void dumpData() {
		DarkObjectStorage_MapDb.LOGGER.info("== Storage Dump ==");
		DarkObjectStorage_MapDb.LOGGER.info("Store Type: "
				+ DarkObjectStorage_MapDb.class.getName());
		for (final Entry<String, ConcurrentNavigableMap<Date, Map<String, String>>> map : this.maps
				.entrySet()) {
			DarkObjectStorage_MapDb.LOGGER.info("Group: " + map.getKey());
			for (final Entry<Date, Map<String, String>> item : map.getValue()
					.entrySet()) {
				DarkObjectStorage_MapDb.LOGGER.info(" -Item: " + item.getKey());
				for (final Entry<String, String> data : item.getValue()
						.entrySet()) {
					DarkObjectStorage_MapDb.LOGGER.info("  +" + data.getKey()
							+ ":" + data.getValue());
				}
			}
		}
	}

	public void findItem(String id) {
		this.getMap(id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<Date, Map<String, String>> getAll(String type) {
		final Map<Date, Map<String, String>> data = this.getMap(type);
		if (data != null && data instanceof HashMap) {
			return (Map<Date, Map<String, String>>) ((HashMap<Date, Map<String, String>>) data)
					.clone();
		}
		return null;
	}

	private DB getDB() {
		if (this.db == null) {
			try {
				this.db = DBMaker.fileDB(this.getDBFile()).closeOnJvmShutdown()
						.make();
			} catch (final IOException e) {
				DarkObjectStorage_MapDb.LOGGER.error("Failed to open DB file",
						e);
			}
		}
		return this.db;
	}

	private File getDBFile() throws IOException {
		if (this.dbFile == null) {
			this.dbFile = new File(
					Configuration
							.getString(Configuration.PROPERTY_DB_FILE_LOCATION),
					this.dbFileName);
			if (!this.dbFile.exists()) {
				this.dbFile.createNewFile();
			}
		}
		return this.dbFile;
	}

	private Map<Date, Map<String, String>> getMap(String mapId) {

		if (this.maps == null) {
			this.maps = new HashMap<String, ConcurrentNavigableMap<Date, Map<String, String>>>();
		}
		ConcurrentNavigableMap<Date, Map<String, String>> output = this.maps
				.get(mapId);
		if (output == null) {
			output = this.getDB().treeMap(mapId);
			this.maps.put(mapId, output);
		}
		return output;
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
		this.getMap(id).put(new Date(), item);
		this.db.commit();
	}
}
