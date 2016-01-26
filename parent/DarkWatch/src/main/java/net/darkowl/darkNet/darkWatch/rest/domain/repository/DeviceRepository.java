/**
 * 
 */
package net.darkowl.darkNet.darkWatch.rest.domain.repository;

import io.katharsis.queryParams.QueryParams;
import io.katharsis.repository.ResourceRepository;

import java.util.UUID;

import net.darkowl.darkNet.darkObjects.devices.BaseDarkDevice;

/**
 * @author Randy Blancett
 * @since Jan 25, 2016
 * 
 */
public class DeviceRepository implements
		ResourceRepository<BaseDarkDevice, UUID> {

	@Override
	public void delete(UUID arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public Iterable<BaseDarkDevice> findAll(Iterable<UUID> arg0,
			QueryParams arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<BaseDarkDevice> findAll(QueryParams arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BaseDarkDevice findOne(UUID arg0, QueryParams arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends BaseDarkDevice> S save(S arg0) {
		// TODO Auto-generated method stub
		return null;
	}

}
