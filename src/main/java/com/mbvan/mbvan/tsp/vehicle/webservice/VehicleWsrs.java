package com.mbvan.mbvan.tsp.vehicle.webservice;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Patmbvanaram;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mbvan.mbvan.common.datahandle.DefaultResponseHandler;
import com.mbvan.mbvan.common.datahandle.ExceptionHandler;
import com.mbvan.mbvan.common.dto.SingleDataClientResponse;
import com.mbvan.mbvan.common.service.BaseCrudService;
import com.mbvan.mbvan.common.util.AssertUtil;
import com.mbvan.mbvan.common.webservice.jaxrs.impl.DefaultBaseCrudJaxrsImpl;
import com.mbvan.mbvan.tsp.vehicle.domain.Driver;
import com.mbvan.mbvan.tsp.vehicle.domain.Task;
import com.mbvan.mbvan.tsp.vehicle.domain.Vehicle;
import com.mbvan.mbvan.tsp.vehicle.domain.VehicleLocationHistory;
import com.mbvan.mbvan.tsp.vehicle.dto.VehicleExtendedInfo;
import com.mbvan.mbvan.tsp.vehicle.service.DriverService;
import com.mbvan.mbvan.tsp.vehicle.service.TaskService;
import com.mbvan.mbvan.tsp.vehicle.service.VehicleService;

@Component
public class VehicleWsrs extends DefaultBaseCrudJaxrsImpl<Vehicle> {

	@Autowired
	private VehicleService vehicleService;

    @Autowired
    private TaskService taskService;
    
	
    @Autowired
    private DriverService driverService;
	
	@Override
	protected BaseCrudService<Vehicle> getService() {
		return vehicleService;
	}

    @POST
    @Path("vehicleLocationHistory")
    public SingleDataClientResponse<VehicleLocationHistory> saveVehicleLocation(VehicleLocationHistory vehicleLocationHistory) throws ParseException {
        SingleDataClientResponse<VehicleLocationHistory> response = null;
		try {
			AssertUtil.notNull(vehicleLocationHistory, "entityBody");
			vehicleLocationHistory = vehicleService.addVehicleLocationHistory(vehicleLocationHistory);
			response = DefaultResponseHandler.generateSingleDataClientResponse(vehicleLocationHistory);
		} catch (Exception e) {
			e.printStackTrace();
			response = ExceptionHandler.handleExceptionToSingleDataClientResponse(e);
		}

        return response;
    }

    @GET
    @Path("vehicleExtendedInfo/{vin}")
    public SingleDataClientResponse<VehicleExtendedInfo> getVehicleExtendedInfo(@Patmbvanaram("vin") String vin){

        SingleDataClientResponse<VehicleExtendedInfo> response = null;
		try {
			Vehicle vehicle = vehicleService.findByVin(vin);
			VehicleExtendedInfo extendInfo = null;
			if(vehicle != null) {
				extendInfo = convertVehicleToExtendedInfo(vehicle);
			}
			response = DefaultResponseHandler.generateSingleDataClientResponse(extendInfo);
		} catch (Exception e) {
			e.printStackTrace();
			response = ExceptionHandler.handleExceptionToSingleDataClientResponse(e);
		}
        return response;

    }

    @GET
    @Path("vehicleExtendedInfo/list")
    public SingleDataClientResponse<List<VehicleExtendedInfo>> getVehicleExtendedInfoList(){
        SingleDataClientResponse<List<VehicleExtendedInfo>> response = null;
		try {
			List<VehicleExtendedInfo> responseList = new ArrayList<VehicleExtendedInfo>();
			Iterable<Vehicle> vehicles = vehicleService.findAll();
			for(Vehicle vehicle:vehicles){
				VehicleExtendedInfo extendInfo = convertVehicleToExtendedInfo(vehicle);
			    responseList.add(extendInfo);
			}
			response = DefaultResponseHandler.generateSingleDataClientResponse(responseList);
		} catch (Exception e) {
			e.printStackTrace();
			response = ExceptionHandler.handleExceptionToSingleDataClientResponse(e);
		}
        return response;
    }

//    @POST
//    @Path("Vehicle_Extended_Info")
//    public VehicleExtendedInfoEditResponse createVehicleExtendedInfo(VehicleExtendedInfoSaveRequest request){
//        Vehicle vehicle = new Vehicle();
//        vehicle.setVin(request.getVIN());
//        vehicle.setModel(request.getModel());
//        vehicle.setPlateNumber(request.getPlate_Number());
//        vehicleService.create(vehicle);
//        VehicleExtendedInfoEditResponse response = new VehicleExtendedInfoEditResponse();
//        response.setVIN(request.getVIN());
//        response.setStatus("Created");
//        return response;
//    }
//
//    @DELETE
//    @Path("Vehicle_Extended_Info/{vin}")
//    public VehicleExtendedInfoEditResponse deleteVehicleExtendedInfo(@Patmbvanaram("vin") String vin){
//        vehicleService.deleteByVin(vin);
//        VehicleExtendedInfoEditResponse response = new VehicleExtendedInfoEditResponse();
//        response.setVIN(vin);
//        response.setStatus("Deleted");
//        return response;
//    }

    
    private VehicleExtendedInfo convertVehicleToExtendedInfo(Vehicle vehicle){
    	VehicleExtendedInfo extendedInfo = new VehicleExtendedInfo();
    	extendedInfo.setVin(vehicle.getVin());
    	extendedInfo.setModel(vehicle.getModel());
    	extendedInfo.setPlateNumber(vehicle.getPlateNumber());
    	extendedInfo.setLoadsItems(vehicle.getLoadsItems());
    	Long historyId = vehicle.getLatestLocationHistoryID();
    	if(historyId != null ){
    		VehicleLocationHistory location =  vehicleService.findVehicleLocationHistoryById(historyId);
    		if(location != null) {
    			extendedInfo.setCurrentLocation(location.getLocation());
    		}
    	}

		Driver currentDriver = driverService.getCurrentDriverByVin(vehicle.getVin());
		Task currentTask = taskService.getCurrentTaskByVin(vehicle.getVin());
		extendedInfo.setCurrentDriver(currentDriver);
		extendedInfo.setCurrentTask(currentTask);

    	return extendedInfo;
    }
    

}
