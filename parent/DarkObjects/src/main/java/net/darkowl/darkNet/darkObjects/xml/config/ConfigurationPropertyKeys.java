package net.darkowl.darkNet.darkObjects.xml.config;

/**
 * This is a set of keys that are available in a default DarkNetConfig.xsd
 * 
 * @author Randy Blancett
 * @since Jan 14, 2016
 * 
 */
public enum ConfigurationPropertyKeys {
	INTERVAL_SECONDS("INTERVAL_SECONDS"), IP("IP");
	private final String key;

	ConfigurationPropertyKeys(String key) {
		this.key = key;
	}

	/**
	 * Get the Key
	 * 
	 * @since Jan 14, 2016
	 * @return the key
	 */
	public String getKey() {
		return key;
	}
}
