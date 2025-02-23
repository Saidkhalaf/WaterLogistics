package be.kdg.sa.water.service;

import be.kdg.sa.water.controller.dto.ShipDto;
import be.kdg.sa.water.domain.Captain;
import be.kdg.sa.water.domain.Ship;
import be.kdg.sa.water.repository.CaptainRepository;
import be.kdg.sa.water.repository.ShipRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ShipService {

    private final Logger logger = LoggerFactory.getLogger(ShipService.class);
    private final ShipRepository shipRepository;
    private final CaptainRepository captainRepository;

    public ShipService(ShipRepository shipRepository, CaptainRepository captainRepository) {
        this.shipRepository = shipRepository;
        this.captainRepository = captainRepository;
    }

    public List<ShipDto> getAllShips() {
        return shipRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public ShipDto getShipById(UUID id) {
        logger.info("Fetching ship with id: {}", id);
        return shipRepository.findById(id)
                .map(this::convertToDto)
                .orElseThrow(() -> new RuntimeException("Ship not found"));
    }

    public ShipDto createShip(ShipDto ShipDto) {
        Ship ship = convertToEntity(ShipDto);
        ship = shipRepository.save(ship);
        return convertToDto(ship);
    }

    public ShipDto updateShip(UUID id, ShipDto ShipDto) {
        Ship ship = convertToEntity(ShipDto);
        ship.setId(id);
        ship = shipRepository.save(ship);
        return convertToDto(ship);
    }

    public void deleteShip(UUID id) {
        shipRepository.deleteById(id);
    }

    private ShipDto convertToDto(Ship ship) {
        return new ShipDto(
                ship.getVesselNumber(),
                ship.getCaptain().getId()
        );
    }

    private Ship convertToEntity(ShipDto dto) {
        logger.info("Converting ShipDto to Ship entity");
        Ship ship = new Ship();
        ship.setVesselNumber(dto.vesselNumber());
        Captain captain = captainRepository.findById(dto.captainId())
                .orElseThrow(() -> new RuntimeException("Captain not found"));
        ship.setCaptain(captain);
        return ship;
    }
}
