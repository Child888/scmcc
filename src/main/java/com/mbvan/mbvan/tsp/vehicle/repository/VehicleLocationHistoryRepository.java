package com.mbvan.mbvan.tsp.vehicle.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.mbvan.mbvan.tsp.vehicle.domain.VehicleLocationHistory;

@Repository
public interface VehicleLocationHistoryRepository extends PagingAndSortingRepository<VehicleLocationHistory, Long> {
	

}