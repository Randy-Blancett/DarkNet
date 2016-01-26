/**
 * 
 */
package net.darkowl.darkNet.darkWatch.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

import net.darkowl.darkNet.darkObjects.devices.BaseDarkDevice;
import net.darkowl.darkNet.darkWatch.rest.interfaces.DevicesServiceInterface;
import net.darkowl.darkNet.darkWatch.rest.service.DefaultDeviceService;

/**
 * Rest controler for devices
 * 
 * @author Randy Blancett
 * @since Jan 16, 2016
 * 
 */
@Path("devices")
public class DevicesController extends AbsDarkNetController {
	private DevicesServiceInterface deviceService = null;

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public List<BaseDarkDevice> get(@Context HttpHeaders headers) {
		final DevicesServiceInterface deviceSvc = this.getDeviceSvc();
		return deviceSvc.getDevices();
	}

	/**
	 * @since Jan 18, 2016
	 * @return the deviceSvc
	 */
	public DevicesServiceInterface getDeviceSvc() {
		if (this.deviceService == null) {
			this.deviceService = new DefaultDeviceService();
		}
		return this.deviceService;
	}

	/**
	 * @since Jan 18, 2016
	 * @param deviceSvc
	 *            the deviceSvc to set
	 */
	public void setDeviceSvc(DevicesServiceInterface deviceSvc) {
		this.deviceService = deviceSvc;
	}
}
