package net.darkowl.darkNet.darkObjects.storage;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

import net.darkowl.darnNet.darkObjects.json.radioThermostat.Time;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DarkTestStorageTest {
	DarkTestStorage store = null;

	@Before
	public void setUp() throws Exception {
		this.store = new DarkTestStorage();
	}

	@After
	public void tearDown() throws Exception {
		this.store.closeConnection();
	}

	@Test
	public void testStorage() {
		final Map<String, String> data = new HashMap<String, String>();
		data.put("Test1", "Value1");
		data.put("Test2", "Value2");
		data.put("Test3", "Value3");

		this.store.storeItem("Test", data);

		final Map<Date, Map<String, String>> testItems = this.store
				.getAll("Test");

		Assert.assertEquals(1, testItems.size());

		for (final Entry<Date, Map<String, String>> item : testItems.entrySet()) {
			Assert.assertEquals(item.getValue().get("Test1"), "Value1");
			Assert.assertEquals(item.getValue().get("Test2"), "Value2");
			Assert.assertEquals(item.getValue().get("Test3"), "Value3");
		}
	}

	@Test
	public void testStoreChangeNewItems() throws InterruptedException {
		Time time1 = new Time(1, 12, 22);
		Map<String, String> data = time1.toMap();

		this.store.storeChange(Time.class.getName(), data);
		Map<Date, Map<String, String>> testItems = this.store.getAll(Time.class
				.getName());
		Assert.assertEquals(1, testItems.size());
		for (int i = 0; i < 5; i++) {
			// Need to wait atleast one second so that we can get a new date
			TimeUnit.SECONDS.sleep(1);
			this.store.storeChange(Time.class.getName(), data);
		}
		testItems = this.store.getAll(Time.class.getName());
		Assert.assertEquals(2, testItems.size());

		// Make sure we can store changes
		// Need to wait atleast one second so that we can get a new date
		TimeUnit.SECONDS.sleep(1);

		Time time2 = new Time(1, 11, 22);
		data = time2.toMap();
		this.store.storeChange(Time.class.getName(), data);
		testItems = this.store.getAll(Time.class.getName());
		Assert.assertEquals(3, testItems.size());

		for (int i = 0; i < 5; i++) {
			// Need to wait atleast one second so that we can get a new date
			TimeUnit.SECONDS.sleep(1);
			this.store.storeChange(Time.class.getName(), data);
		}
		testItems = this.store.getAll(Time.class.getName());
		Assert.assertEquals(4, testItems.size());
	}

}
