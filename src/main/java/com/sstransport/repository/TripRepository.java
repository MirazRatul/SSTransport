package com.sstransport.repository;

import com.sstransport.model.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TripRepository extends JpaRepository<Trip, Integer> {

    List<Trip> findByDate(LocalDateTime date);

    List<Trip> findByPickupDest(String pickupDest);

    List<Trip> findByDropDest(String dropDest);

    List<Trip> findByClientName(String clientName);

    // ✅ FIXED: Integer
    List<Trip> findByDriverId(Integer driverId);

    List<Trip> findByHelperId(Integer helperId);

    List<Trip> findByVehicleId(Integer vehicleId);

    List<Trip> findByStatus(String status);

    List<Trip> findByGoodsType(String goodsType);

    List<Trip> findByClientContact(String clientContact);

    // ✅ EXTRA (useful)
    List<Trip> findByDateBetween(LocalDateTime start, LocalDateTime end);

    // Get 3 most recent trips for a vehicle
    List<Trip> findTop3ByVehicleIdOrderByDateDesc(Integer vehicleId);
}