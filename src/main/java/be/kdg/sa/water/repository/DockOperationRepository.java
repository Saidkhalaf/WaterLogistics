package be.kdg.sa.water.repository;

import be.kdg.sa.water.domain.DockOperation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface DockOperationRepository extends JpaRepository<DockOperation, UUID> {
    List<DockOperation> findByShipId(UUID shipId);
}
