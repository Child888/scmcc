package com.mbvan.mbvan.tsp.vehicle.service;

import org.springframework.transaction.annotation.Transactional;

import com.mbvan.mbvan.common.service.BaseCrudService;
import com.mbvan.mbvan.tsp.vehicle.domain.Driver;
import com.mbvan.mbvan.tsp.vehicle.domain.DriverVan;

@Transactional(readOnly = true)
public interface DriverService extends BaseCrudService<Driver> {

	
	public DriverVan linkDriverToVan(DriverVan driverVan);
	
	public DriverVan getCurrentDriverVanByDriver(Long driverId);
	
	public Driver getCurrentDriverByVin(String vin);
	
	public Long unLinkDriverFromVan(String vin) ;
	
	
	
}
