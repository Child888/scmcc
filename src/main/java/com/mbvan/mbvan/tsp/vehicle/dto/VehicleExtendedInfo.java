package com.mbvan.mbvan.tsp.vehicle.dto;

import com.mbvan.mbvan.tsp.vehicle.domain.Driver;
import com.mbvan.mbvan.tsp.vehicle.domain.Task;


public class VehicleExtendedInfo {
    private String vin;
    private String model;
    private String plateNumber;
    private String loadsItems;
    private String currentLocation;
    private Driver currentDriver;
    private Task currentTask;
	public String getVin() {
		return vin;
	}
	public void setVin(String vin) {
		this.vin = vin;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getPlateNumber() {
		return plateNumber;
	}
	public void setPlateNumber(String plateNumber) {
		this.plateNumber = plateNumber;
	}
	public String getCurrentLocation() {
		return currentLocation;
	}
	public void setCurrentLocation(String currentLocation) {
		this.currentLocation = currentLocation;
	}
	public Driver getCurrentDriver() {
		return currentDriver;
	}
	public void setCurrentDriver(Driver currentDriver) {
		this.currentDriver = currentDriver;
	}
	public Task getCurrentTask() {
		return currentTask;
	}
	public void setCurrentTask(Task currentTask) {
		this.currentTask = currentTask;
	}
	public String getLoadsItems() {
		return loadsItems;
	}
	public void setLoadsItems(String loadsItems) {
		this.loadsItems = loadsItems;
	}


}
