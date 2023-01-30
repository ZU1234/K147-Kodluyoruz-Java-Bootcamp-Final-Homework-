package com.biletx.repository;

import com.biletx.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Integer> {


    @Query(value = "SELECT * FROM vehicles WHERE from_where ILIKE %?% AND where_to ILIKE %?%", nativeQuery = true)
    List<Vehicle> findAllByProvince(String fromWhere, String whereTo);

    @Query(value = "SELECT * FROM vehicles WHERE departure_time = ? ", nativeQuery = true)
    List<Vehicle> findAllByDate(String departureTime);
    @Query(value = "SELECT * FROM vehicles WHERE status = 'ACTIVE' ", nativeQuery = true)
    List<Vehicle> findAllByActive();
}


