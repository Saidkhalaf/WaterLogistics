package be.kdg.sa.water.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class LoadingOperation {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String cargoType;
    private double quantity;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime loadingTime;

    @ManyToOne
    private Ship ship;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getCargoType() {
        return cargoType;
    }

    public void setCargoType(String cargoType) {
        this.cargoType = cargoType;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public LocalDateTime getLoadingTime() {
        return loadingTime;
    }

    public void setLoadingTime(LocalDateTime loadingTime) {
        this.loadingTime = loadingTime;
    }

    public Ship getShip() {
        return ship;
    }

    public void setShip(Ship ship) {
        this.ship = ship;
    }
}
