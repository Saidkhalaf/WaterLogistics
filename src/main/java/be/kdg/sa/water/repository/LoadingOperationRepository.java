package be.kdg.sa.water.repository;

import be.kdg.sa.water.domain.LoadingOperation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface LoadingOperationRepository extends JpaRepository<LoadingOperation, UUID> {
    List<LoadingOperation> findByShipId(UUID shipId);
}
