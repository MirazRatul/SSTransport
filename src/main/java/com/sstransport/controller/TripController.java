package com.sstransport.controller;

import com.sstransport.model.Trip;
import com.sstransport.repository.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime; // ✅ FIXED
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/trips")
public class TripController {

    @Autowired
    private TripRepository tripRepository;

    // Get all trips
    @GetMapping
    public List<Trip> getAllTrips() {
        return tripRepository.findAll();
    }

    // Get trip by ID
    @GetMapping("/{id}")
    public ResponseEntity<Trip> getTripById(@PathVariable Integer id) {
        Optional<Trip> trip = tripRepository.findById(id);
        return trip.map(ResponseEntity::ok)
                   .orElse(ResponseEntity.notFound().build());
    }

    // ✅ FIXED: LocalDateTime instead of LocalDate
    @GetMapping("/date/{date}")
    public List<Trip> getTripsByDate(@PathVariable LocalDateTime date) {
        return tripRepository.findByDate(date);
    }

    // Get trips by pickup destination
    @GetMapping("/pickup/{pickupDest}")
    public List<Trip> getTripsByPickupDest(@PathVariable String pickupDest) {
        return tripRepository.findByPickupDest(pickupDest);
    }

    // Get trips by drop destination
    @GetMapping("/drop/{dropDest}")
    public List<Trip> getTripsByDropDest(@PathVariable String dropDest) {
        return tripRepository.findByDropDest(dropDest);
    }

    // Get trips by client name
    @GetMapping("/client/{clientName}")
    public List<Trip> getTripsByClientName(@PathVariable String clientName) {
        return tripRepository.findByClientName(clientName);
    }

    // Integer IDs (correct)
    @GetMapping("/driver/{driverId}")
    public List<Trip> getTripsByDriverId(@PathVariable Integer driverId) {
        return tripRepository.findByDriverId(driverId);
    }

    @GetMapping("/helper/{helperId}")
    public List<Trip> getTripsByHelperId(@PathVariable Integer helperId) {
        return tripRepository.findByHelperId(helperId);
    }

    @GetMapping("/vehicle/{vehicleId}")
    public List<Trip> getTripsByVehicleId(@PathVariable Integer vehicleId) {
        return tripRepository.findByVehicleId(vehicleId);
    }

    // Get trips by status
    @GetMapping("/status/{status}")
    public List<Trip> getTripsByStatus(@PathVariable String status) {
        return tripRepository.findByStatus(status);
    }

    // Get trips by goods type
    @GetMapping("/goods/{goodsType}")
    public List<Trip> getTripsByGoodsType(@PathVariable String goodsType) {
        return tripRepository.findByGoodsType(goodsType);
    }

    // Get trips by client contact
    @GetMapping("/contact/{clientContact}")
    public List<Trip> getTripsByClientContact(@PathVariable String clientContact) {
        return tripRepository.findByClientContact(clientContact);
    }

    // Create new trip
    @PostMapping
    public ResponseEntity<Trip> createTrip(@RequestBody Trip trip) {

        if (trip.getDate() == null ||
            trip.getPickupDest() == null || trip.getPickupDest().isEmpty() ||
            trip.getDropDest() == null || trip.getDropDest().isEmpty() ||
            trip.getClientName() == null || trip.getClientName().isEmpty() ||
            trip.getClientContact() == null || trip.getClientContact().isEmpty() ||
            trip.getDriverId() == null ||
            trip.getVehicleId() == null ||
            trip.getFare() == null) {

            return ResponseEntity.badRequest().build();
        }

        // Default status
        if (trip.getStatus() == null || trip.getStatus().isEmpty()) {
            trip.setStatus("PENDING");
        }

        Trip savedTrip = tripRepository.save(trip);
        return ResponseEntity.ok(savedTrip);
    }

    // Update trip
    @PutMapping("/{id}")
    public ResponseEntity<Trip> updateTrip(@PathVariable Integer id, @RequestBody Trip tripDetails) {

        Optional<Trip> optionalTrip = tripRepository.findById(id);
        if (!optionalTrip.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Trip trip = optionalTrip.get();

        if (tripDetails.getDate() != null) trip.setDate(tripDetails.getDate());
        if (tripDetails.getPickupDest() != null && !tripDetails.getPickupDest().isEmpty()) trip.setPickupDest(tripDetails.getPickupDest());
        if (tripDetails.getDropDest() != null && !tripDetails.getDropDest().isEmpty()) trip.setDropDest(tripDetails.getDropDest());
        if (tripDetails.getClientName() != null && !tripDetails.getClientName().isEmpty()) trip.setClientName(tripDetails.getClientName());
        if (tripDetails.getClientContact() != null && !tripDetails.getClientContact().isEmpty()) trip.setClientContact(tripDetails.getClientContact());

        if (tripDetails.getDriverId() != null) trip.setDriverId(tripDetails.getDriverId());
        if (tripDetails.getHelperId() != null) trip.setHelperId(tripDetails.getHelperId());
        if (tripDetails.getVehicleId() != null) trip.setVehicleId(tripDetails.getVehicleId());

        if (tripDetails.getStatus() != null) trip.setStatus(tripDetails.getStatus());
        if (tripDetails.getFare() != null) trip.setFare(tripDetails.getFare());
        if (tripDetails.getGoodsType() != null) trip.setGoodsType(tripDetails.getGoodsType());

        Trip updatedTrip = tripRepository.save(trip);
        return ResponseEntity.ok(updatedTrip);
    }

    // Delete trip
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTrip(@PathVariable Integer id) {
        if (!tripRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        tripRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}