package net.darkowl.darkNet.darkWatch.rest.domain.model;

import io.katharsis.resource.annotations.JsonApiId;
import io.katharsis.resource.annotations.JsonApiResource;

import java.util.UUID;

/**
 * @author Randy Blancett
 * @since Jan 25, 2016
 * 
 */

@JsonApiResource(type = "1devices")
public class Device {
	@JsonApiId
	private UUID id;

	private String name;

	/**
	 * @since Jan 25, 2016
	 * @return the id
	 */
	public UUID getId() {

		return this.id;
	}

	/**
	 * @since Jan 25, 2016
	 * @return the name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * @since Jan 25, 2016
	 * @param id
	 *            the id to set
	 */
	public void setId(UUID id) {
		this.id = id;
	}

	/**
	 * @since Jan 25, 2016
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

}
