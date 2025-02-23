package be.kdg.sa.water.repository;

import be.kdg.sa.water.domain.Ship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public interface ShipRepository extends JpaRepository<Ship, UUID> {
    Optional<Ship> findByVesselNumber(String shipNumber);
}
