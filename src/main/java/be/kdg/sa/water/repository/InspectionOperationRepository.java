package be.kdg.sa.water.repository;

import be.kdg.sa.water.domain.InspectionOperation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface InspectionOperationRepository extends JpaRepository<InspectionOperation, UUID> {
    List<InspectionOperation> findInspectionOperationsByIsOpenTrue();
    List<InspectionOperation> findInspectionOperationsByIsDoneTrue();
    List<InspectionOperation> findByDockOperation_ShipId(UUID shipId);
}
