package net.darkowl.darkNet.darkWatch.config;

import io.katharsis.locator.SampleJsonServiceLocator;
import io.katharsis.rs.KatharsisFeature;
import io.katharsis.rs.KatharsisProperties;

import java.net.InetAddress;
import java.net.URI;
import java.net.UnknownHostException;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.UriBuilder;

import net.darkowl.darkNet.darkObjects.config.Configuration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.glassfish.jersey.server.ResourceConfig;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * this class will configure the Rest service for DarkWatch
 * 
 * @author Randy Blancett
 * @since Jan 25, 2016
 * 
 */
@ApplicationPath("/")
public class RestServerConfig extends ResourceConfig {
	private final static Logger LOGGER = LogManager
			.getLogger(RestServerConfig.class);

	private static int port = 0;

	private static String getHostName() {
		String hostName = "localhost";
		try {
			hostName = InetAddress.getLocalHost().getCanonicalHostName();
		} catch (final UnknownHostException e) {
			RestServerConfig.LOGGER.error("Failed to get IP address.", e);
		}
		return hostName;
	}

	public static URI getRestURI() {
		if (RestServerConfig.port == 0) {
			RestServerConfig.port = Configuration
					.getInteger(WatchConfig.PROPERTY_REST_SERVER_PORT);
			if (RestServerConfig.port == 0) {
				RestServerConfig.port = 8977;
			}
		}
		return UriBuilder
				.fromUri("http://" + RestServerConfig.getHostName() + "/")
				.port(RestServerConfig.port).build();
	}

	public RestServerConfig() {
		this.packages(true, "net.darkowl.darkNet.darkWatch.rest");
		this.property(KatharsisProperties.RESOURCE_SEARCH_PACKAGE,
				"net.darkowl.darkNet.darkWatch.rest.domain");
		this.property(KatharsisProperties.RESOURCE_DEFAULT_DOMAIN,
				RestServerConfig.getRestURI().toString());
		this.register(new KatharsisFeature(new ObjectMapper(),
				new SampleJsonServiceLocator()));
	}

}
