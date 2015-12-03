/**
 * 
 */
package net.darkowl.darkNet.darkObjects.test.restEndpoints;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.entity.StringEntity;
import org.apache.http.protocol.HttpContext;
import org.apache.http.protocol.HttpRequestHandler;

/**
 * @author Randy Blancett
 * @since Dec 2, 2015
 * 
 */
public class RadioThermostat_tstat implements HttpRequestHandler {

	public static enum ResponseType {
		TYPE_404, TYPE_200_GOOD_DATA;
	}

	private static ResponseType type = ResponseType.TYPE_404;

	public static void setType(ResponseType typeData) {
		type = typeData;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.http.protocol.HttpRequestHandler#handle(org.apache.http.
	 * HttpRequest, org.apache.http.HttpResponse,
	 * org.apache.http.protocol.HttpContext)
	 */
	@Override
	public void handle(final HttpRequest request, final HttpResponse response,
			final HttpContext context) throws HttpException, IOException {
		setResponse(response);
	}

	private void setResponse(final HttpResponse response)
			throws UnsupportedEncodingException {
		switch (type) {
		case TYPE_404:
			response.setStatusCode(HttpStatus.SC_NOT_FOUND);
			response.addHeader("Content-Type", "application/json");
			response.setEntity(new StringEntity("{\"test\":\"test1\"}"));
			break;
		case TYPE_200_GOOD_DATA:
			response.setStatusCode(HttpStatus.SC_OK);
			response.addHeader("Content-Type", "application/json");
			response.setEntity(new StringEntity(
					"{\"temp\":65.00,\"tmode\":1,\"fmode\":0,\"override\":0,\"hold\":0,\"t_heat\":65.00,\"tstate\":0,\"fstate\":0,\"time\":{\"day\":2,\"hour\":18,\"minute\":21},\"t_type_post\":0}"));
			break;
		}
	}
}
