package be.kdg.sa.water.repository;

import be.kdg.sa.water.domain.BunkerOperation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface BunkerOperationRepository extends JpaRepository<BunkerOperation, UUID> {
    List<BunkerOperation> findByOperationDate(LocalDate operationDate);
    List<BunkerOperation> findByShipId(UUID shipId);
}
