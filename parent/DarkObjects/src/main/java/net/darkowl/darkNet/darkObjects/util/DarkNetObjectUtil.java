/**
 * 
 */
package net.darkowl.darkNet.darkObjects.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import net.darkowl.darkNet.darkObjects.interfaces.DarkNetDAO;

/**
 * @author Randy Blancett
 * @since Jan 3, 2016
 * 
 */
public class DarkNetObjectUtil {

	@SuppressWarnings("unchecked")
	public static DarkNetDAO form(Map<String, String> map)
			throws ClassNotFoundException, NoSuchMethodException,
			SecurityException, InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {
		String type = map.get(DarkNetDAO.COL_TYPE);
		DarkNetDAO objectInstance = null;
		Class<DarkNetDAO> object;
		object = (Class<DarkNetDAO>) Class.forName(type);
		final Constructor<DarkNetDAO> constructor = object
				.getConstructor(Map.class);
		objectInstance = constructor.newInstance(map);
		return objectInstance;
	}
}
