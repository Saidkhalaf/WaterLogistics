package be.kdg.sa.water.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class DockOperation {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String vesselNumber;

    private LocalDateTime shipArrivalTime;
    private LocalDateTime shipDepartureTime;

    @ManyToOne
    private Ship ship;

    @OneToOne
    private PurchaseOrder purchaseOrder;

    @OneToOne(mappedBy = "dockOperation")
    private InspectionOperation inspectionOperation;

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

    public LocalDateTime getShipArrivalTime() {
        return shipArrivalTime;
    }

    public void setShipArrivalTime(LocalDateTime shipArrivalTime) {
        this.shipArrivalTime = shipArrivalTime;
    }

    public LocalDateTime getShipDepartureTime() {
        return shipDepartureTime;
    }

    public void setShipDepartureTime(LocalDateTime shipDepartureTime) {
        this.shipDepartureTime = shipDepartureTime;
    }

    public Ship getShip() {
        return ship;
    }

    public void setShip(Ship ship) {
        this.ship = ship;
    }

    public PurchaseOrder getPurchaseOrder() {
        return purchaseOrder;
    }

    public void setPurchaseOrder(PurchaseOrder purchaseOrder) {
        this.purchaseOrder = purchaseOrder;
    }

    public InspectionOperation getInspectionOperation() {
        return inspectionOperation;
    }

    public void setInspectionOperation(InspectionOperation inspectionOperation) {
        this.inspectionOperation = inspectionOperation;
    }
}
