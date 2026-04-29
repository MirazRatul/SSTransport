package com.sstransport.controller;

import com.sstransport.dto.VehicleCrewDTO;
import com.sstransport.dto.VehicleDetailDTO;
import com.sstransport.model.Vehicle;
import com.sstransport.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/vehicles")
public class VehicleController {

    @Autowired
    private VehicleRepository vehicleRepository;

    // Get all vehicles
    @GetMapping
    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    // Get vehicle by ID
    @GetMapping("/{id}")
    public ResponseEntity<Vehicle> getVehicleById(@PathVariable Integer id) {
        Optional<Vehicle> vehicle = vehicleRepository.findById(id);
        return vehicle.map(ResponseEntity::ok)
                      .orElse(ResponseEntity.notFound().build());
    }

    // Get vehicle details with driver and helper information
    @GetMapping("/details/{id}")
    public ResponseEntity<VehicleDetailDTO> getVehicleDetails(@PathVariable Integer id) {
        Optional<VehicleDetailDTO> vehicleDetail = vehicleRepository.getVehicleDetailsWithEmployees(id);
        return vehicleDetail.map(ResponseEntity::ok)
                            .orElse(ResponseEntity.notFound().build());
    }

    // Get vehicle by registration number
    @GetMapping("/reg/{regNumber}")
    public ResponseEntity<Vehicle> getVehicleByRegNumber(@PathVariable String regNumber) {
        Optional<Vehicle> vehicle = vehicleRepository.findByRegNumber(regNumber);
        return vehicle.map(ResponseEntity::ok)
                      .orElse(ResponseEntity.notFound().build());
    }

    // Get vehicles by size
    @GetMapping("/size/{vehicleSize}")
    public List<Vehicle> getVehiclesBySize(@PathVariable String vehicleSize) {
        return vehicleRepository.findByVehicleSize(vehicleSize);
    }

    // Get vehicles by capacity
    @GetMapping("/capacity/{capacity}")
    public List<Vehicle> getVehiclesByCapacity(@PathVariable String capacity) {
        return vehicleRepository.findByCapacity(capacity);
    }

    // Get vehicles by assigned driver
    @GetMapping("/driver/{assignedDriver}")
    public List<Vehicle> getVehiclesByAssignedDriver(@PathVariable Integer assignedDriver) {
        return vehicleRepository.findByAssignedDriver(assignedDriver);
    }

    // Get vehicles by assigned helper
    @GetMapping("/helper/{assignedHelper}")
    public List<Vehicle> getVehiclesByAssignedHelper(@PathVariable Integer assignedHelper) {
        return vehicleRepository.findByAssignedHelper(assignedHelper);
    }

    // Get vehicles by last maintenance date
    @GetMapping("/maintenance/{lastMaintenanceDate}")
    public List<Vehicle> getVehiclesByLastMaintenanceDate(@PathVariable LocalDate lastMaintenanceDate) {
        return vehicleRepository.findByLastMaintenanceDate(lastMaintenanceDate);
    }
    
    // Get vehicles by crew (driver and helper details)
    @GetMapping("/crew/{vehicleId}")
    public ResponseEntity<VehicleCrewDTO> getVehicleCrew(@PathVariable Integer vehicleId) {
        Optional<VehicleCrewDTO> crew = vehicleRepository.getVehicleCrew(vehicleId);
        return crew.map(ResponseEntity::ok)
                   .orElse(ResponseEntity.notFound().build());
    }

    // Create new vehicle
    @PostMapping
    public ResponseEntity<Vehicle> createVehicle(@RequestBody Vehicle vehicle) {
        // Check required fields
        if (vehicle.getRegNumber() == null || vehicle.getRegNumber().isEmpty()) {
            return ResponseEntity.badRequest().body(null);
        }

        // Check uniqueness of registration number
        if (vehicleRepository.existsByRegNumber(vehicle.getRegNumber())) {
            return ResponseEntity.badRequest().body(null);
        }

        Vehicle savedVehicle = vehicleRepository.save(vehicle);
        return ResponseEntity.ok(savedVehicle);
    }

    // Update vehicle
    @PutMapping("/{id}")
    public ResponseEntity<Vehicle> updateVehicle(@PathVariable Integer id, @RequestBody Vehicle vehicleDetails) {
        Optional<Vehicle> optionalVehicle = vehicleRepository.findById(id);
        if (!optionalVehicle.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Vehicle vehicle = optionalVehicle.get();

        if (vehicleDetails.getRegNumber() != null && !vehicleDetails.getRegNumber().isEmpty()) vehicle.setRegNumber(vehicleDetails.getRegNumber());
        if (vehicleDetails.getVehicleSize() != null && !vehicleDetails.getVehicleSize().isEmpty()) vehicle.setVehicleSize(vehicleDetails.getVehicleSize());
        if (vehicleDetails.getCapacity() != null && !vehicleDetails.getCapacity().isEmpty()) vehicle.setCapacity(vehicleDetails.getCapacity());
        if (vehicleDetails.getAssignedDriver() != null) vehicle.setAssignedDriver(vehicleDetails.getAssignedDriver());
        if (vehicleDetails.getAssignedHelper() != null) vehicle.setAssignedHelper(vehicleDetails.getAssignedHelper());
        if (vehicleDetails.getRegCard() != null) vehicle.setRegCard(vehicleDetails.getRegCard());
        if (vehicleDetails.getFitnessCertificate() != null) vehicle.setFitnessCertificate(vehicleDetails.getFitnessCertificate());
        if (vehicleDetails.getLastMaintenanceDate() != null) vehicle.setLastMaintenanceDate(vehicleDetails.getLastMaintenanceDate());
        if (vehicleDetails.getPartsFixed() != null) vehicle.setPartsFixed(vehicleDetails.getPartsFixed());

        Vehicle updatedVehicle = vehicleRepository.save(vehicle);
        return ResponseEntity.ok(updatedVehicle);
    }

    // Delete vehicle
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVehicle(@PathVariable Integer id) {
        if (!vehicleRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        vehicleRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
