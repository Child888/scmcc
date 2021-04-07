package com.mbvan.mbvan.tsp.vehicle.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.mbvan.mbvan.common.service.BaseCrudService;
import com.mbvan.mbvan.tsp.vehicle.domain.TaskAssignment;

/**
 * Created with IntelliJ IDEA.
 * User: fenliang
 * Date: 1/11/13
 * Time: 4:03 PM
 * To change this template use File | Settings | File Templates.
 */
@Transactional(readOnly = true)
public interface TaskAssignmentService extends BaseCrudService<TaskAssignment> {
	
	public TaskAssignment assignTask(TaskAssignment taskAssignment);
	
	public void unassignTask(Long taskAssignmentId);
	
	public List<TaskAssignment> findTaskAssignmentListByVin(String vin);
	
}
