package net.darkowl.darkNet.darkObjects.json.radioThermostat;

import java.net.URL;

public enum RadioThermostatTestJson {
	TIME_1(RadioThermostatTestJson.class
			.getResource("/json/radioThermostat/time_1.json")), TSTAT_1(
			RadioThermostatTestJson.class
					.getResource("/json/radioThermostat/tstat_1.json"));
	private final String filename;

	RadioThermostatTestJson(URL fileURL) {
		filename = fileURL.getFile();
	}

	/**
	 * @since Dec 3, 2015
	 * @return the filename
	 */
	public String getFilename() {
		return filename;
	}
}
