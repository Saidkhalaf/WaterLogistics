package be.kdg.sa.water.domain;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
public class BunkerOperation {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String vesselNumber;

    @ManyToOne
    private Ship ship;

    private LocalDate operationDate;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getVesselNumber() {
        return vesselNumber;
    }

    public void setVesselNumber(String vesselNumber) {
        this.vesselNumber = vesselNumber;
    }

    public Ship getShip() {
        return ship;
    }

    public void setShip(Ship ship) {
        this.ship = ship;
    }

    public LocalDate getOperationDate() {
        return operationDate;
    }

    public void setOperationDate(LocalDate operationDate) {
        this.operationDate = operationDate;
    }
}
