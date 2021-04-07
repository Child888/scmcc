package com.mbvan.mbvan.tsp.vehicle.webservice;

import java.util.ArrayList;
import java.util.List;

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
import com.mbvan.mbvan.tsp.vehicle.domain.Task;
import com.mbvan.mbvan.tsp.vehicle.domain.TaskAssignment;
import com.mbvan.mbvan.tsp.vehicle.dto.TaskDetailInfo;
import com.mbvan.mbvan.tsp.vehicle.service.DriverService;
import com.mbvan.mbvan.tsp.vehicle.service.TaskAssignmentService;
import com.mbvan.mbvan.tsp.vehicle.service.TaskService;

@Component
@Produces(MediaType.APPLICATION_JSON)
public class TaskWsrs extends DefaultBaseCrudJaxrsImpl<Task> {

    @Autowired
    private TaskService taskService;


    @Autowired
    private DriverService driverService;
    
	@Autowired
    private TaskAssignmentService taskAssignmentService;
	
	
	@Override
	protected BaseCrudService<Task> getService() {
		return taskService;
	}
	
	
    @POST
    @Path("taskAssignment")
    public SingleDataClientResponse<TaskAssignment> taskAssignment(TaskAssignment taskAssignment){
    	SingleDataClientResponse<TaskAssignment> response = null;
		try {
			taskAssignment = taskAssignmentService.assignTask(taskAssignment);
			response = DefaultResponseHandler.generateSingleDataClientResponse(taskAssignment);
		} catch (Exception e) {
			e.printStackTrace();
			response = ExceptionHandler.handleExceptionToSingleDataClientResponse(e);
		}
        return response;
    }
    
    @POST
    @Path("unassign/{taskAssignmentId}")
    public SingleDataClientResponse<Long> unassignTask(@Patmbvanaram("taskAssignmentId") Long taskAssignmentId){
    	SingleDataClientResponse<Long> response = null;
		try {
			taskAssignmentService.unassignTask(taskAssignmentId);
			response = DefaultResponseHandler.generateSingleDataClientResponse(taskAssignmentId);
		} catch (Exception e) {
			e.printStackTrace();
			response = ExceptionHandler.handleExceptionToSingleDataClientResponse(e);
		}
        return response;
    }
    
    

    @GET
    @Path("taskDetailList/{vin}")
    public SingleDataClientResponse<List<TaskDetailInfo>> findTaskDetailListByVin(@Patmbvanaram("vin") String vin){
    	SingleDataClientResponse<List<TaskDetailInfo>> response = null;
		try {
			List<TaskAssignment> taList = taskAssignmentService.findTaskAssignmentListByVin(vin);
			List<TaskDetailInfo> taskDetailList = new ArrayList<TaskDetailInfo>();
			for(TaskAssignment ta : taList){
				TaskDetailInfo taskDetail = convertTaskDetailInfo(ta);
				taskDetailList.add(taskDetail);
			}
			response = DefaultResponseHandler.generateSingleDataClientResponse(taskDetailList);
		} catch (Exception e) {
			e.printStackTrace();
			response = ExceptionHandler.handleExceptionToSingleDataClientResponse(e);
		}
        return response;
    }
    
    @GET
    @Path("unassignedTaskList")
    public SingleDataClientResponse<List<Task>> findUnassignedTasks(){
    	SingleDataClientResponse<List<Task>> response = null;
		try {
			List<Task> taskList = taskService.findUnassignedTasks();
			response = DefaultResponseHandler.generateSingleDataClientResponse(taskList);
		} catch (Exception e) {
			e.printStackTrace();
			response = ExceptionHandler.handleExceptionToSingleDataClientResponse(e);
		}
        return response;
    }
    

//    @DELETE
//    @Path("/{id}")
//    public ClientResponseWithHeader deleteTask(@Patmbvanaram("id") Long id){
//        taskService.delete(id);
//        ClientResponseWithHeader response = DefaultResponseHandler.generateBlankResponse();
//        return response;
//    }
//    
    
    private TaskDetailInfo convertTaskDetailInfo(TaskAssignment taskAssignment){
    	if(taskAssignment != null){
    		TaskDetailInfo taskDetailInfo = new TaskDetailInfo();
    		taskDetailInfo.setTaskAssignmentId(taskAssignment.getId());
    		String vin = taskAssignment.getVin();
    		taskDetailInfo.setVin(vin);
    		
			Driver driver = driverService.getCurrentDriverByVin(vin);
			if(driver != null){
				taskDetailInfo.setDriverId(driver.getId());
				taskDetailInfo.setDriverName(driver.getName());
			}

    		
    		Long taskId = taskAssignment.getTaskId();
    		if(taskId != null){
    			Task task = taskService.findById(taskId);
    			if(task != null){
    				taskDetailInfo.setTask(task);
//    				taskDetailInfo.setTaskId(task.getId());
//    				taskDetailInfo.setName(task.getName());
//    				taskDetailInfo.setDescription(task.getDescription());
//    				taskDetailInfo.setLocation(task.getLocation());
//    				taskDetailInfo.setCustomerDetails(task.getCustomerDetails());
//    				taskDetailInfo.setStartTime(task.getStartTime());
//    				taskDetailInfo.setDueTime(task.getDueTime());
    			}
    		}
    		

    		return taskDetailInfo;
    	}
    	return null;
    }
    

	
}
