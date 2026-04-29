package com.sstransport.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "trip")
public class Trip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // ✅ FIXED: LocalDateTime for DATETIME
    @Column(name = "date", nullable = false)
    private LocalDateTime date;

    @Column(name = "pickup_dest", length = 255, nullable = false)
    private String pickupDest;

    @Column(name = "drop_dest", length = 255, nullable = false)
    private String dropDest;

    @Column(name = "client_name", length = 45, nullable = false)
    private String clientName;

    @Column(name = "client_contact", length = 15, nullable = false)
    private String clientContact;

    // ✅ FIXED: Integer IDs
    @Column(name = "driver_id", nullable = false)
    private Integer driverId;

    @Column(name = "helper_id")
    private Integer helperId;

    @Column(name = "vehicle_id", nullable = false)
    private Integer vehicleId;

    @Column(name = "status", length = 20)
    private String status;

    @Column(name = "fare", nullable = false)
    private Integer fare;

    @Column(name = "goods_type", length = 50)
    private String goodsType;

    // Constructors
    public Trip() {}

    public Trip(LocalDateTime date, String pickupDest, String dropDest,
                String clientName, String clientContact,
                Integer driverId, Integer helperId, Integer vehicleId,
                String status, Integer fare, String goodsType) {
        this.date = date;
        this.pickupDest = pickupDest;
        this.dropDest = dropDest;
        this.clientName = clientName;
        this.clientContact = clientContact;
        this.driverId = driverId;
        this.helperId = helperId;
        this.vehicleId = vehicleId;
        this.status = status;
        this.fare = fare;
        this.goodsType = goodsType;
    }

    // Getters & Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public LocalDateTime getDate() { return date; }
    public void setDate(LocalDateTime date) { this.date = date; }

    public String getPickupDest() { return pickupDest; }
    public void setPickupDest(String pickupDest) { this.pickupDest = pickupDest; }

    public String getDropDest() { return dropDest; }
    public void setDropDest(String dropDest) { this.dropDest = dropDest; }

    public String getClientName() { return clientName; }
    public void setClientName(String clientName) { this.clientName = clientName; }

    public String getClientContact() { return clientContact; }
    public void setClientContact(String clientContact) { this.clientContact = clientContact; }

    public Integer getDriverId() { return driverId; }
    public void setDriverId(Integer driverId) { this.driverId = driverId; }

    public Integer getHelperId() { return helperId; }
    public void setHelperId(Integer helperId) { this.helperId = helperId; }

    public Integer getVehicleId() { return vehicleId; }
    public void setVehicleId(Integer vehicleId) { this.vehicleId = vehicleId; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Integer getFare() { return fare; }
    public void setFare(Integer fare) { this.fare = fare; }

    public String getGoodsType() { return goodsType; }
    public void setGoodsType(String goodsType) { this.goodsType = goodsType; }
}