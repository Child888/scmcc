package com.mbvan.mbvan.tsp.vehicle.dto;

import com.mbvan.mbvan.tsp.vehicle.domain.Task;


public class TaskDetailInfo {
	private Long taskAssignmentId;
	
	private String vin;
	private Long driverId;
	private String driverName;
	
	private Task task;
	
	
	public Long getTaskAssignmentId() {
		return taskAssignmentId;
	}
	public void setTaskAssignmentId(Long taskAssignmentId) {
		this.taskAssignmentId = taskAssignmentId;
	}

	public String getVin() {
		return vin;
	}
	public void setVin(String vin) {
		this.vin = vin;
	}
	public Long getDriverId() {
		return driverId;
	}
	public void setDriverId(Long driverId) {
		this.driverId = driverId;
	}
	public String getDriverName() {
		return driverName;
	}
	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}
	public Task getTask() {
		return task;
	}
	public void setTask(Task task) {
		this.task = task;
	}
	
	
	
}
