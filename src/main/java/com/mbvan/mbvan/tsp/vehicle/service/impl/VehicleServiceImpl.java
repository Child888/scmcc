package com.mbvan.mbvan.tsp.vehicle.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mbvan.mbvan.common.service.impl.DefaultCrudService;
import com.mbvan.mbvan.tsp.vehicle.domain.Vehicle;
import com.mbvan.mbvan.tsp.vehicle.domain.VehicleLocationHistory;
import com.mbvan.mbvan.tsp.vehicle.repository.VehicleLocationHistoryRepository;
import com.mbvan.mbvan.tsp.vehicle.repository.VehicleRepository;
import com.mbvan.mbvan.tsp.vehicle.service.VehicleService;


@Service()
public class VehicleServiceImpl extends DefaultCrudService<Vehicle> implements VehicleService{

	@Autowired
	private VehicleRepository defaultRepository;
    
    @Autowired
    private VehicleLocationHistoryRepository vehicleLocationHistoryRepository;
    
	

	
	@Override
	public PagingAndSortingRepository<Vehicle, Long> getDefaultRepository() {
		return defaultRepository;
	}

    @Override
    @Transactional(readOnly = true)
    public void deleteByVin(String VIN) {
        defaultRepository.deleteByVin(VIN);
    }

    @Override
    public Vehicle findByVin(String VIN) {
        return defaultRepository.findByVin(VIN);
    }

//    @Override
//    public CurrentDriverAndTask getCurrentDriverAndTask(String VIN) {
//        CurrentDriverAndTask currentDriverAndTask = new CurrentDriverAndTask();
//        List<TaskAssignment> taskAssignments = taskAssignmentRepository.findByVinAndStatus(VIN, "Saved");
//        if(!taskAssignments.isEmpty()){
//            TaskAssignment taskAssignment = taskAssignments.get(0);
//            Task task = taskRepository.findOne(taskAssignment.getTaskId());
//            if(null != task)
//                currentDriverAndTask.setTask(task);
//            Driver driver = driverRepository.findOne(taskAssignment.getDriverId());
//            if(null != driver)
//                currentDriverAndTask.setDriver(driver);
//        }
//        return currentDriverAndTask;
//    }

    
	@Override
	@Transactional
	public VehicleLocationHistory addVehicleLocationHistory(VehicleLocationHistory vehicleLocationHistory) {
		vehicleLocationHistory = vehicleLocationHistoryRepository.save(vehicleLocationHistory);
		String vin = vehicleLocationHistory.getVin();
		Vehicle vehicle = findByVin(vin);
		if(vehicle != null){
			vehicle.setLatestLocationHistoryID(vehicleLocationHistory.getId());
			this.update(vehicle);
		}
		else{
			System.out.println("can't find this vehicle");
		}
		return vehicleLocationHistory;
	}

	@Override
	public VehicleLocationHistory findVehicleLocationHistoryById(Long vehicleLocationHistoryId) {
		return vehicleLocationHistoryRepository.findOne(vehicleLocationHistoryId);
	}

	

	
}
