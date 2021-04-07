package com.mbvan.mbvan.tsp.vehicle.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.mbvan.mbvan.tsp.vehicle.domain.Driver;

@Repository
public interface DriverRepository extends PagingAndSortingRepository<Driver, Long> {
	
//	List<Driver> findByCarProfileId(Long carProfileId);
//
//	Driver getByCarProfileId(Long carProfileId);
//	
//	@Transactional()
//	@Modifying
//	@Query("update BatteryInfo u set u.isCharging = ?2,u.scheduleChargeTime = ?3 where u.id = ?1")
//	void updateCharging(Long id,Boolean isCharging,Date scheduleChargeTime);
}