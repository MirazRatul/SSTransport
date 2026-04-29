package com.sstransport.repository;

import com.sstransport.dto.VehicleCrewDTO;
import com.sstransport.dto.VehicleDetailDTO;
import com.sstransport.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Integer> {

	Optional<Vehicle> findByRegNumber(String regNumber);

	List<Vehicle> findByVehicleSize(String vehicleSize);

	List<Vehicle> findByCapacity(String capacity);

	List<Vehicle> findByAssignedDriver(Integer assignedDriver);

	List<Vehicle> findByAssignedHelper(Integer assignedHelper);

	List<Vehicle> findByLastMaintenanceDate(LocalDate lastMaintenanceDate);

	boolean existsByRegNumber(String regNumber);

	@Query("SELECT new com.sstransport.dto.VehicleDetailDTO("
			+ "v.id, v.regNumber, v.vehicleSize, v.capacity, v.regCard, v.fitnessCertificate, v.lastMaintenanceDate, v.partsFixed, "
			+ "d.id, d.name, d.role, d.image, " + "h.id, h.name, h.role, h.image) " + "FROM Vehicle v "
			+ "LEFT JOIN Employee d ON v.assignedDriver = d.id " + "LEFT JOIN Employee h ON v.assignedHelper = h.id "
			+ "WHERE v.id = :vehicleId")
	Optional<VehicleDetailDTO> getVehicleDetailsWithEmployees(@Param("vehicleId") Integer vehicleId);

	@Query("SELECT new com.sstransport.dto.VehicleCrewDTO(" + "d.id, d.name, d.contact, d.role, "
			+ "h.id, h.name, h.contact, h.role) " + "FROM Vehicle v "
			+ "LEFT JOIN Employee d ON v.assignedDriver = d.id " + "LEFT JOIN Employee h ON v.assignedHelper = h.id "
			+ "WHERE v.id = :vehicleId")
	Optional<VehicleCrewDTO> getVehicleCrew(@Param("vehicleId") Integer vehicleId);
}
