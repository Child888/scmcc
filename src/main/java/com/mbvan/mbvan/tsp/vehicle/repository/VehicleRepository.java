package com.mbvan.mbvan.tsp.vehicle.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mbvan.mbvan.tsp.vehicle.domain.Vehicle;

@Repository
public interface VehicleRepository extends PagingAndSortingRepository<Vehicle, Long> {
	
	public Vehicle findByVin(String vin);

    @Transactional()
	@Modifying
    @Query("delete from Vehicle v where v.vin = ?1")
    public void deleteByVin(String vin);

}