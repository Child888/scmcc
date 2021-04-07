package com.mbvan.mbvan.tsp.vehicle.webservice;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Patmbvanaram;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mbvan.mbvan.common.datahandle.DefaultResponseHandler;
import com.mbvan.mbvan.common.datahandle.ExceptionHandler;
import com.mbvan.mbvan.common.dto.SingleDataClientResponse;
import com.mbvan.mbvan.common.service.BaseCrudService;
import com.mbvan.mbvan.common.webservice.jaxrs.impl.DefaultBaseCrudJaxrsImpl;
import com.mbvan.mbvan.tsp.vehicle.domain.Driver;
import com.mbvan.mbvan.tsp.vehicle.domain.DriverVan;
import com.mbvan.mbvan.tsp.vehicle.service.DriverService;

@Component
@Produces(MediaType.APPLICATION_JSON)
public class DriverWsrs extends DefaultBaseCrudJaxrsImpl<Driver>{

    @Autowired
    private DriverService driverService;


	@Override
	protected BaseCrudService<Driver> getService() {
		return driverService;
	}

    @POST
    @Path("linkDriverToVan")
	public SingleDataClientResponse<DriverVan> linkDriverToVan(DriverVan driverVan) {
    	SingleDataClientResponse<DriverVan> response = null;
		try {
			driverVan = driverService.linkDriverToVan(driverVan);
			response = DefaultResponseHandler.generateSingleDataClientResponse(driverVan);
		} catch (Exception e) {
			e.printStackTrace();
			response = ExceptionHandler.handleExceptionToSingleDataClientResponse(e);
		}
        return response;
	}

    @POST
    @Path("unLinkDriverFromVan/{vin}")
	public SingleDataClientResponse<Long> unLinkDriverFromVan(@Patmbvanaram("vin") String vin) {
    	SingleDataClientResponse<Long> response = null;
		try {
			Long driverId = driverService.unLinkDriverFromVan(vin);
			response = DefaultResponseHandler.generateSingleDataClientResponse(driverId);
		} catch (Exception e) {
			e.printStackTrace();
			response = ExceptionHandler.handleExceptionToSingleDataClientResponse(e);
		}
        return response;
	}    
    
    
    @GET
    @Path("{driverId}/currentDriverVan")
    public SingleDataClientResponse<DriverVan>  getCurrentDriverVanByDriver(@Patmbvanaram("driverId") Long driverId) {
    	SingleDataClientResponse<DriverVan> response = null;
		try {
			DriverVan driverVan = driverService.getCurrentDriverVanByDriver(driverId);
			response = DefaultResponseHandler.generateSingleDataClientResponse(driverVan);
		} catch (Exception e) {
			e.printStackTrace();
			response = ExceptionHandler.handleExceptionToSingleDataClientResponse(e);
		}
        return response;
    }
    
	
}
