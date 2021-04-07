package com.mbvan.mbvan.tsp.vehicle.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mbvan.mbvan.common.service.impl.DefaultCrudService;
import com.mbvan.mbvan.common.util.AssertUtil;
import com.mbvan.mbvan.tsp.vehicle.domain.Driver;
import com.mbvan.mbvan.tsp.vehicle.domain.DriverVan;
import com.mbvan.mbvan.tsp.vehicle.repository.DriverRepository;
import com.mbvan.mbvan.tsp.vehicle.repository.DriverVanRepository;
import com.mbvan.mbvan.tsp.vehicle.service.DriverService;

@Service
public class DriverServiceImpl extends DefaultCrudService<Driver>	implements DriverService {

	@Autowired
	private DriverRepository repository;
	
	@Autowired
	private DriverVanRepository driverVanRepository;
	
	@Override
	protected PagingAndSortingRepository<Driver, Long> getDefaultRepository() {
		return repository;
	}


	@Override
	@Transactional
	public DriverVan linkDriverToVan(DriverVan driverVan) {
		String vin = driverVan.getVin();
		Long driverId = driverVan.getDriverId();
		AssertUtil.notEmpty(vin, " vin ");
		AssertUtil.notNull(driverId, " driverId ");
		List<DriverVan> cuByVans = driverVanRepository.getCurrentDriverVanByVin(vin);
		for(DriverVan dv : cuByVans){
			dv.setCurrentFlag("0");
		}
		driverVanRepository.save(cuByVans);
		
		List<DriverVan> cuByDriver = driverVanRepository.getCurrentDriverVanByDriver(driverId);
		for(DriverVan dv : cuByDriver){
			dv.setCurrentFlag("0");
		}
		driverVanRepository.save(cuByDriver);
		driverVan.setId(null);
		driverVan.setCurrentFlag("1");
		driverVan = driverVanRepository.save(driverVan);
		return driverVan;
	}

	@Override
	@Transactional
	public Long unLinkDriverFromVan(String vin) {
		AssertUtil.notEmpty(vin, " vin ");
		List<DriverVan> cuByVans = driverVanRepository.getCurrentDriverVanByVin(vin);
		Long driverId = null;
		for(DriverVan dv : cuByVans){
			if("1".equals(dv.getCurrentFlag())){
				driverId = dv.getDriverId();
				driverVanRepository.delete(dv);
			}	
		}

		return driverId;
	}
	
	

	@Override
	public DriverVan getCurrentDriverVanByDriver(Long driverId) {
		DriverVan currentDriverVan = null;
		List<DriverVan> driverVans = driverVanRepository.getCurrentDriverVanByDriver(driverId);
		if(driverVans != null && driverVans.size() > 0){
			currentDriverVan = driverVans.get(0);
		}
		return currentDriverVan;
	}

	
	@Override
	public Driver getCurrentDriverByVin(String vin) {
		Driver driver = null;
		DriverVan driverVan = getCurrentDriverVanByVin(vin);
		if(driverVan != null){
			Long driverId = driverVan.getDriverId();
    		if(driverId != null){
    			driver = this.findById(driverId);
    		}
		}
		return driver;
	}
	
	
	private DriverVan getCurrentDriverVanByVin(String vin) {
		DriverVan currentDriverVan = null;
		List<DriverVan> driverVans = driverVanRepository.getCurrentDriverVanByVin(vin);
		if(driverVans != null && driverVans.size() > 0){
			currentDriverVan = driverVans.get(0);
		}
		return currentDriverVan;
	}

}
