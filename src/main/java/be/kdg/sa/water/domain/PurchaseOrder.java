package be.kdg.sa.water.domain;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
public class PurchaseOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String orderNumber;

    @ManyToOne
    private Ship ship;

    @ElementCollection(targetClass = PurchaseOrderStatus.class)
    @Enumerated(EnumType.STRING)
    private List<PurchaseOrderStatus> status;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Ship getShip() {
        return ship;
    }

    public void setShip(Ship ship) {
        this.ship = ship;
    }

    public List<PurchaseOrderStatus> getStatus() {
        return status;
    }

    public void setStatus(List<PurchaseOrderStatus> status) {
        this.status = status;
    }
}
