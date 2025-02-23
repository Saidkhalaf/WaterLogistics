package be.kdg.sa.water.repository;

import be.kdg.sa.water.domain.Captain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CaptainRepository extends JpaRepository<Captain, UUID> {
}
