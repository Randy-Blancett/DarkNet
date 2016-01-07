/**
 * 
 */
package net.darkowl.darkNet.darkObjects.storage;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import net.darkowl.darkNet.darkObjects.interfaces.DarkDataStorage;
import net.darkowl.darkNet.darkObjects.interfaces.DarkNetDAO;
import net.darkowl.darkNet.darkObjects.util.DarkNetObjectUtil;

/**
 * @author Randy Blancett
 * @since Dec 8, 2015
 * 
 */
public class DarkTestStorage implements DarkDataStorage {
	Map<String, Map<Date, Map<String, String>>> storage = null;

	// List<Map<String, String>> storage = new ArrayList<Map<String, String>>();

	/**
	 * Clear all data in the store
	 * 
	 * @since Dec 9, 2015
	 */
	public void clear() {
		this.storage = null;
	}

	@Override
	public void closeConnection() {
		this.clear();
	}

	@Override
	public void dumpData() {
		System.out.println("Data dump not implemented");

	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<Date, Map<String, String>> getAll(String type) {
		return (Map<Date, Map<String, String>>) ((TreeMap<Date, Map<String, String>>) this
				.getTypeStorage(type)).clone();
	}

	/**
	 * Get the size of the storage
	 * 
	 * @since Dec 9, 2015
	 * @return
	 */
	public int getSize() {
		return this.storage.size();
	}

	protected Map<String, Map<Date, Map<String, String>>> getStorage() {
		if (this.storage == null) {
			this.storage = new HashMap<String, Map<Date, Map<String, String>>>();
		}
		return this.storage;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected TreeMap<Date, Map<String, String>> getTypeStorage(String type) {
		final Map<String, Map<Date, Map<String, String>>> store = this
				.getStorage();
		Map<Date, Map<String, String>> output = store.get(type);
		if (output == null) {
			output = new TreeMap<Date, Map<String, String>>();
			store.put(type, output);
		}
		return (TreeMap) output;
	}

	@Override
	public void storeChange(String id, Map<String, String> item) {
		TreeMap<Date, Map<String, String>> typeStore = getTypeStorage(id);
		if (typeStore.size() >= 2) {
			Entry<Date, Map<String, String>> orig = null;
			Entry<Date, Map<String, String>> old = null;
			for (Entry<Date, Map<String, String>> data : typeStore
					.descendingMap().entrySet()) {
				if (old == null) {
					old = data;
					continue;
				}
				if (orig == null) {
					orig = data;
					continue;
				}

				break;
			}

			try {
				DarkNetDAO oldObj = DarkNetObjectUtil.form(old.getValue());
				DarkNetDAO newObj = DarkNetObjectUtil.form(item);

				// Check if the old data stored was a change we only want to
				// capture the first time a value showed up and the last time it
				// showed up
				// Check if the new and the old are the same if not then we can
				// just add it because it is a change
				if (!oldObj.hasChanged(newObj)) {
					DarkNetDAO origObj = DarkNetObjectUtil
							.form(orig.getValue());
					// the new data and the old object is the same. WE need to
					// see if the orig and the old are the same
					if (!origObj.hasChanged(oldObj)) {
						// Orig and old are the same so we need to remove old or
						// we would have 3 of the same value
						typeStore.remove(old.getKey());
					}
				}
			} catch (ClassNotFoundException | NoSuchMethodException
					| SecurityException | InstantiationException
					| IllegalAccessException | IllegalArgumentException
					| InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		storeItem(id, item);
	}

	@Override
	public void storeItem(String id, Map<String, String> item) {
		this.getTypeStorage(id).put(new Date(), item);
	}
}
