package com.mbvan.mbvan.tsp.vehicle.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.mbvan.mbvan.tsp.vehicle.domain.Task;

@Repository
public interface TaskRepository extends PagingAndSortingRepository<Task, Long> {

	@Query(" select t from Task t where t.id not in (select ta.taskId from TaskAssignment ta ) order by t.createTime ")
	public List<Task> findUnassignedTasks();
	
	
	@Query(" select t from Task t, TaskAssignment ta where t.id =ta.taskId and vin=?1 and (t.status <> 'finished' or t.finishTime is null) order by t.createTime  ")
	public List<Task> findCurrentTasksByVin(String vin);
	
	@Override
	@Query(" select t from Task t order by t.createTime " )
	public List<Task> findAll();

}