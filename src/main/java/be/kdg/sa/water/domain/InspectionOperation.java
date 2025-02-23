package be.kdg.sa.water.domain;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class InspectionOperation {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private LocalDateTime inspectionTime;
    private String inspectionNumber;

    private boolean isOpen;
    private boolean isDone;

    @OneToOne
    private DockOperation dockOperation;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDateTime getInspectionTime() {
        return inspectionTime;
    }

    public void setInspectionTime(LocalDateTime inspectionTime) {
        this.inspectionTime = inspectionTime;
    }

    public String getInspectionNumber() {
        return inspectionNumber;
    }

    public void setInspectionNumber(String inspectionNumber) {
        this.inspectionNumber = inspectionNumber;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public DockOperation getDockOperation() {
        return dockOperation;
    }

    public void setDockOperation(DockOperation dockOperation) {
        this.dockOperation = dockOperation;
    }
}
