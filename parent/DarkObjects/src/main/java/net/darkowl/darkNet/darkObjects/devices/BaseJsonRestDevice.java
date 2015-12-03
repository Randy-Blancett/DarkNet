/**
 * 
 */
package net.darkowl.darkNet.darkObjects.devices;

import java.io.IOException;
import java.util.List;

import net.darkowl.darkNet.darkObjects.xml.config.Configuration;

import org.apache.http.HttpEntity;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

/**
 * This will provide specifics for devices that use Json restful endpoints
 * 
 * @author Randy Blancett
 * @since Nov 29, 2015
 * 
 */
public abstract class BaseJsonRestDevice extends BaseRestDevice {
	/**
	 * Create a basic Uknown device
	 * 
	 * @since Nov 29, 2015
	 */
	public BaseJsonRestDevice() {
		super("Unknown", null);
	}

	/**
	 * Create a device and pass it to parent to use
	 * 
	 * @since Nov 29, 2015
	 * @param deviceName
	 *            Device Name
	 * @param configs
	 *            Configurations
	 */
	public BaseJsonRestDevice(String deviceName, List<Configuration> configs) {
		super(deviceName, configs);

	}

	private final static Logger LOGGER = LogManager
			.getLogger(BaseJsonRestDevice.class);

	private JSONParser parser = new JSONParser();

	/**
	 * This will call a rest endpoint and turn it into the class passed in with
	 * Clazz
	 * 
	 * @since Dec 3, 2015
	 * @param endpoint
	 *            endpoint to get json object
	 * @param clazz
	 *            Class expected
	 * @return generic json object
	 */
	public <T> Object getJson(String endpoint, Class<T> clazz) {
		HttpEntity data = getData(endpoint);
		if (data == null) {
			return null;
		}
		LOGGER.info("Content Type:" + data.getContentType());
		LOGGER.info(data.toString());

		Gson gson = new GsonBuilder().create();
		T obj = null;
		try {
			obj = gson.fromJson(EntityUtils.toString(data), clazz);
		} catch (JsonSyntaxException | IOException
				| org.apache.http.ParseException e) {
			LOGGER.error("Failed to parse data from :" + endpoint, e);
		}
		return obj;
	}

	/**
	 * get a json object from a rest service
	 * 
	 * @since Dec 2, 2015
	 * @param endpoint
	 *            endpoint to get json object
	 * @return generic json object
	 */
	public JSONObject getJson(String endpoint) {
		HttpEntity obj = getData(endpoint);
		if (obj == null) {
			return null;
		}
		LOGGER.info("Content Type:" + obj.getContentType());
		LOGGER.info(obj.toString());
		try {
			return (JSONObject) parser.parse(EntityUtils.toString(obj));
		} catch (UnsupportedOperationException | IOException | ParseException e) {
			LOGGER.error("Failed to parse returned data into JSON", e);
		}
		return null;
	}
}
