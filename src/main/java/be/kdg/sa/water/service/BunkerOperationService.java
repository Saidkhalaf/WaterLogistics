package be.kdg.sa.water.service;

import be.kdg.sa.water.controller.dto.BunkerOperationDto;
import be.kdg.sa.water.domain.BunkerOperation;
import be.kdg.sa.water.repository.BunkerOperationRepository;
import be.kdg.sa.water.repository.ShipRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BunkerOperationService {

    private final Logger logger = LoggerFactory.getLogger(BunkerOperationService.class);
    private static final int MAX_OPERATIONS_PER_DAY = 6;

    private final BunkerOperationRepository bunkerOperationRepository;
    private final ShipRepository shipRepository;

    public BunkerOperationService(BunkerOperationRepository bunkerOperationRepository, ShipRepository shipRepository) {
        this.bunkerOperationRepository = bunkerOperationRepository;
        this.shipRepository = shipRepository;
    }

    public boolean planBunkerOperation(BunkerOperation bunkerOperation) {
        logger.info("Planning bunker operation for shipId: {}", bunkerOperation.getShip().getId());
        LocalDate operationDate = bunkerOperation.getOperationDate();
        List<BunkerOperation> operations = bunkerOperationRepository.findByOperationDate(operationDate);

        if (operations.size() >= MAX_OPERATIONS_PER_DAY) {
            logger.error("Maximum number of bunker operations per day reached for date: {}", operationDate);
            return false;
        }

        if (!shipRepository.existsById(bunkerOperation.getShip().getId())) {
            logger.error("Ship with ID {} does not exist", bunkerOperation.getShip().getId());
            throw new IllegalArgumentException("Ship with ID " + bunkerOperation.getShip().getId() + " does not exist");
        }

        bunkerOperationRepository.save(bunkerOperation);
        return true;

    }

    public List<BunkerOperationDto> getAllBunkerOperations() {
        return bunkerOperationRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public BunkerOperationDto getBunkerOperationById(UUID id) {
        return bunkerOperationRepository.findById(id)
                .map(this::convertToDto)
                .orElseThrow(() -> new RuntimeException("BunkerOperation not found"));
    }

    public BunkerOperationDto createBunkerOperation(BunkerOperationDto BunkerOperationDto) {
        BunkerOperation bunkerOperation = convertToEntity(BunkerOperationDto);
        bunkerOperation = bunkerOperationRepository.save(bunkerOperation);
        return convertToDto(bunkerOperation);
    }

    public BunkerOperationDto updateBunkerOperation(UUID id, BunkerOperationDto BunkerOperationDto) {
        BunkerOperation bunkerOperation = convertToEntity(BunkerOperationDto);
        bunkerOperation.setId(id);
        bunkerOperation = bunkerOperationRepository.save(bunkerOperation);
        return convertToDto(bunkerOperation);
    }

    public void deleteBunkerOperation(UUID id) {
        bunkerOperationRepository.deleteById(id);
    }

    private BunkerOperationDto convertToDto(BunkerOperation bunkerOperation) {
        return new BunkerOperationDto(
                bunkerOperation.getId(),
                bunkerOperation.getVesselNumber(),
                bunkerOperation.getShip().getId()
        );
    }

    private BunkerOperation convertToEntity(BunkerOperationDto dto) {
        BunkerOperation bunkerOperation = new BunkerOperation();
        bunkerOperation.setVesselNumber(dto.vesselNumber());
        // Set other fields and relationships
        return bunkerOperation;
    }
}
