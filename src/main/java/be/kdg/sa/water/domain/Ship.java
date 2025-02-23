package be.kdg.sa.water.domain;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
public class Ship {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String vesselNumber;

    @OneToMany(mappedBy = "ship")
    private List<DockOperation> dockOperations;

    @OneToMany(mappedBy = "ship")
    private List<BunkerOperation> bunkerOperations;

    @OneToMany(mappedBy = "ship")
    private List<PurchaseOrder> purchaseOrders;

    @OneToMany(mappedBy = "ship")
    private List<LoadingOperation> loadingOperations;

    @ManyToOne
    private Captain captain;

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

    public List<DockOperation> getDockOperations() {
        return dockOperations;
    }

    public void setDockOperations(List<DockOperation> dockOperations) {
        this.dockOperations = dockOperations;
    }

    public List<BunkerOperation> getBunkerOperations() {
        return bunkerOperations;
    }

    public void setBunkerOperations(List<BunkerOperation> bunkerOperations) {
        this.bunkerOperations = bunkerOperations;
    }

    public List<PurchaseOrder> getPurchaseOrders() {
        return purchaseOrders;
    }

    public void setPurchaseOrders(List<PurchaseOrder> purchaseOrders) {
        this.purchaseOrders = purchaseOrders;
    }

    public Captain getCaptain() {
        return captain;
    }

    public void setCaptain(Captain captain) {
        this.captain = captain;
    }

    public List<LoadingOperation> getLoadingOperations() {
        return loadingOperations;
    }

    public void setLoadingOperations(List<LoadingOperation> loadingOperations) {
        this.loadingOperations = loadingOperations;
    }
}
