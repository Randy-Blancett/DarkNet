/**
 * 
 */
package net.darkowl.darkNet.darkWatch.rest.providers;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.Collection;

import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;

import net.darkowl.darkNet.darkObjects.Output;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author Randy Blancett
 * @since Jan 17, 2016
 * 
 */
@Provider
@Produces(MediaType.TEXT_PLAIN)
public class TextPlainWriter implements MessageBodyWriter<Object> {
	private final static Logger LOGGER = LogManager
			.getLogger(TextPlainWriter.class);

	private String digger(Class<?> clazz, Object obj) {
		final StringBuilder output = new StringBuilder();
		final Class<?> parent = clazz.getSuperclass();
		if (parent != null) {
			output.append(this.digger(parent, obj));
		}
		for (final Field field : clazz.getDeclaredFields()) {
			if (field.getAnnotation(Output.class) != null) {
				field.setAccessible(true);
				try {
					output.append(field.getName());
					output.append(":");
					output.append(field.get(obj));
					output.append("\n");
				} catch (IllegalArgumentException | IllegalAccessException e) {
					TextPlainWriter.LOGGER.error("Failed to declare data.", e);
				}
			}
		}
		return output.toString();
	}

	@Override
	public long getSize(Object arg0, Class<?> arg1, Type arg2,
			Annotation[] arg3, MediaType arg4) {
		// deprecated by JAX-RS 2.0 and ignored by Jersey runtime
		return -1;
	}

	@Override
	public boolean isWriteable(Class<?> type, Type genericType,
			Annotation[] annotations, MediaType mediaType) {
		return true;
	}

	@SuppressWarnings("rawtypes")
	private String object2String(Object obj) {
		final Class<? extends Object> clazz = obj.getClass();
		final StringBuilder output = new StringBuilder();

		if (obj instanceof Collection) {
			for (final Object data : (Collection) obj) {
				output.append(this.object2String(data));
			}
		} else {
			output.append(this.digger(clazz, obj));
		}
		return output.toString();
	}

	@Override
	public void writeTo(Object object, Class<?> type, Type genericType,
			Annotation[] annotations, MediaType mediaType,
			MultivaluedMap<String, Object> httpHeaders,
			OutputStream entityStream) throws IOException,
			WebApplicationException {

		entityStream.write(this.object2String(object).getBytes());
	}

}
