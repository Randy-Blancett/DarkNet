/**
 * 
 */
package net.darkowl.darkNet.darkObjects.interfaces;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import net.darkowl.darkNet.darkObjects.Watched;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author Randy Blancett
 * @since Jan 6, 2016
 * 
 */
public abstract class AbstractDarkNetDAO implements DarkNetDAO {
	private final static Logger LOGGER = LogManager
			.getLogger(AbstractDarkNetDAO.class);

	/**
	 * This will run through all the fields and check for equal
	 * 
	 * @since Jan 6, 2016
	 * @param methods
	 *            All fields of a given object
	 * @param orig
	 *            original object (must have all fields that are passed in)
	 * @param other
	 *            Object to compair to (must have all fields that are passed in)
	 * @return
	 */
	protected boolean hasChanged(Method[] methods, Object orig, Object other) {
		for (Method method : methods) {
			if (Modifier.isStatic(method.getModifiers())
					|| !method.getName().startsWith("get")) {
				continue;
			}
			Watched watched = method.getAnnotation(Watched.class);
			if (watched == null || !watched.enabled()) {
				continue;
			}
			try {
				Object origValue = method.invoke(orig);
				Object otherValue = method.invoke(other);
				if (origValue == null) {
					if (otherValue != null) {
						return true;
					}
				} else {
					if (!origValue.equals(otherValue)) {
						return true;
					}
				}
			} catch (InvocationTargetException | IllegalArgumentException
					| IllegalAccessException e) {
				LOGGER.error("Failed to compair Object", e);
			}
		}
		return false;
	}

}
