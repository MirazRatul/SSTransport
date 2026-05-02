package com.sstransport.controller;

import com.sstransport.model.Trip;
import com.sstransport.repository.TripRepository;
import com.sstransport.repository.EmployeeRepository;
import com.sstransport.repository.VehicleRepository;
import com.sstransport.dto.TripDetailDTO;
import com.sstransport.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime; // ✅ FIXED
import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

@RestController
@RequestMapping("/api/trips")
public class TripController {

    @Autowired
    private TripRepository tripRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

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

    // Get all trips with driver/helper names and roles
    @GetMapping("/details")
    public List<TripDetailDTO> getAllTripsWithCrew() {
        List<Trip> trips = tripRepository.findAll();
        List<TripDetailDTO> result = new ArrayList<>();

        for (Trip t : trips) {
            Integer driverId = t.getDriverId();
            String driverName = null;
            String driverRole = null;
            String driverImage = null;
            if (driverId != null) {
                Optional<Employee> d = employeeRepository.findById(driverId);
                if (d.isPresent()) {
                    driverName = d.get().getName();
                    driverRole = d.get().getRole();
                    driverImage = d.get().getImage();
                }
            }

            Integer helperId = t.getHelperId();
            String helperName = null;
            String helperRole = null;
            String helperImage = null;
            if (helperId != null) {
                Optional<Employee> h = employeeRepository.findById(helperId);
                if (h.isPresent()) {
                    helperName = h.get().getName();
                    helperRole = h.get().getRole();
                    helperImage = h.get().getImage();
                }
            }

            String vehicleReg = null;
            Integer vehicleId = t.getVehicleId();
            if (vehicleId != null) {
                Optional<com.sstransport.model.Vehicle> v = vehicleRepository.findById(vehicleId);
                if (v.isPresent()) vehicleReg = v.get().getRegNumber();
            }

            TripDetailDTO dto = new TripDetailDTO(
                    t.getId(),
                    t.getDate(),
                    t.getPickupDest(),
                    t.getDropDest(),
                    t.getClientName(),
                    t.getClientContact(),
                    driverId, driverName, driverRole, driverImage,
                    helperId, helperName, helperRole, helperImage,
                    t.getVehicleId(), vehicleReg, t.getStatus(), t.getFare(), t.getGoodsType()
            );

            result.add(dto);
        }

        return result;
    }

    // Get trip details (driver/helper names, roles, images and vehicle reg) by trip id
    @GetMapping("/details/{id}")
    public ResponseEntity<TripDetailDTO> getTripDetailsById(@PathVariable Integer id) {
        Optional<Trip> optionalTrip = tripRepository.findById(id);
        if (!optionalTrip.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Trip t = optionalTrip.get();

        Integer driverId = t.getDriverId();
        String driverName = null;
        String driverRole = null;
        String driverImage = null;
        if (driverId != null) {
            Optional<Employee> d = employeeRepository.findById(driverId);
            if (d.isPresent()) {
                driverName = d.get().getName();
                driverRole = d.get().getRole();
                driverImage = d.get().getImage();
            }
        }

        Integer helperId = t.getHelperId();
        String helperName = null;
        String helperRole = null;
        String helperImage = null;
        if (helperId != null) {
            Optional<Employee> h = employeeRepository.findById(helperId);
            if (h.isPresent()) {
                helperName = h.get().getName();
                helperRole = h.get().getRole();
                helperImage = h.get().getImage();
            }
        }

        String vehicleReg = null;
        Integer vehicleId = t.getVehicleId();
        if (vehicleId != null) {
            Optional<com.sstransport.model.Vehicle> v = vehicleRepository.findById(vehicleId);
            if (v.isPresent()) vehicleReg = v.get().getRegNumber();
        }

        TripDetailDTO dto = new TripDetailDTO(
                t.getId(),
                t.getDate(),
                t.getPickupDest(),
                t.getDropDest(),
                t.getClientName(),
                t.getClientContact(),
                driverId, driverName, driverRole, driverImage,
                helperId, helperName, helperRole, helperImage,
                t.getVehicleId(), vehicleReg, t.getStatus(), t.getFare(), t.getGoodsType()
        );

        return ResponseEntity.ok(dto);
    }

    // Get trip details (driver/helper names, roles, images and vehicle reg) by status
    @GetMapping("/details/status/{status}")
    public List<TripDetailDTO> getTripDetailsByStatus(@PathVariable String status) {
        List<Trip> trips = tripRepository.findByStatus(status);
        List<TripDetailDTO> result = new ArrayList<>();

        for (Trip t : trips) {
            Integer driverId = t.getDriverId();
            String driverName = null;
            String driverRole = null;
            String driverImage = null;
            if (driverId != null) {
                Optional<Employee> d = employeeRepository.findById(driverId);
                if (d.isPresent()) {
                    driverName = d.get().getName();
                    driverRole = d.get().getRole();
                    driverImage = d.get().getImage();
                }
            }

            Integer helperId = t.getHelperId();
            String helperName = null;
            String helperRole = null;
            String helperImage = null;
            if (helperId != null) {
                Optional<Employee> h = employeeRepository.findById(helperId);
                if (h.isPresent()) {
                    helperName = h.get().getName();
                    helperRole = h.get().getRole();
                    helperImage = h.get().getImage();
                }
            }

            String vehicleReg = null;
            Integer vehicleId = t.getVehicleId();
            if (vehicleId != null) {
                Optional<com.sstransport.model.Vehicle> v = vehicleRepository.findById(vehicleId);
                if (v.isPresent()) vehicleReg = v.get().getRegNumber();
            }

            TripDetailDTO dto = new TripDetailDTO(
                    t.getId(),
                    t.getDate(),
                    t.getPickupDest(),
                    t.getDropDest(),
                    t.getClientName(),
                    t.getClientContact(),
                    driverId, driverName, driverRole, driverImage,
                    helperId, helperName, helperRole, helperImage,
                    t.getVehicleId(), vehicleReg, t.getStatus(), t.getFare(), t.getGoodsType()
            );

            result.add(dto);
        }

        return result;
    }

    // Get recent 3 trips (details) for a vehicle
    @GetMapping("/vehicle/{vehicleId}/recent")
    public List<TripDetailDTO> getRecentTripsByVehicleId(@PathVariable Integer vehicleId) {
        List<Trip> trips = tripRepository.findTop3ByVehicleIdOrderByDateDesc(vehicleId);
        List<TripDetailDTO> result = new ArrayList<>();

        for (Trip t : trips) {
            Integer driverId = t.getDriverId();
            String driverName = null;
            String driverRole = null;
            String driverImage = null;
            if (driverId != null) {
                Optional<Employee> d = employeeRepository.findById(driverId);
                if (d.isPresent()) {
                    driverName = d.get().getName();
                    driverRole = d.get().getRole();
                    driverImage = d.get().getImage();
                }
            }

            Integer helperId = t.getHelperId();
            String helperName = null;
            String helperRole = null;
            String helperImage = null;
            if (helperId != null) {
                Optional<Employee> h = employeeRepository.findById(helperId);
                if (h.isPresent()) {
                    helperName = h.get().getName();
                    helperRole = h.get().getRole();
                    helperImage = h.get().getImage();
                }
            }

            String vehicleReg = null;
            Integer vId = t.getVehicleId();
            if (vId != null) {
                Optional<com.sstransport.model.Vehicle> v = vehicleRepository.findById(vId);
                if (v.isPresent()) vehicleReg = v.get().getRegNumber();
            }

            TripDetailDTO dto = new TripDetailDTO(
                    t.getId(),
                    t.getDate(),
                    t.getPickupDest(),
                    t.getDropDest(),
                    t.getClientName(),
                    t.getClientContact(),
                    driverId, driverName, driverRole, driverImage,
                    helperId, helperName, helperRole, helperImage,
                    t.getVehicleId(), vehicleReg, t.getStatus(), t.getFare(), t.getGoodsType()
            );

            result.add(dto);
        }

        return result;
    }
}