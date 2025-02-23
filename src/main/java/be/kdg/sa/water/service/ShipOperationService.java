package be.kdg.sa.water.service;

import be.kdg.sa.water.controller.dto.*;
import be.kdg.sa.water.domain.*;
import be.kdg.sa.water.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ShipOperationService {

    private final Logger logger = LoggerFactory.getLogger(ShipOperationService.class);

    private final BunkerOperationRepository bunkerOperationRepository;
    private final InspectionOperationRepository inspectionOperationRepository;
    private final LoadingOperationRepository loadingOperationRepository;
    private final ShipRepository shipRepository;

    public ShipOperationService(BunkerOperationRepository bunkerOperationRepository, InspectionOperationRepository inspectionOperationRepository, LoadingOperationRepository loadingOperationRepository, ShipRepository shipRepository) {
        this.bunkerOperationRepository = bunkerOperationRepository;
        this.inspectionOperationRepository = inspectionOperationRepository;
        this.loadingOperationRepository = loadingOperationRepository;
        this.shipRepository = shipRepository;
    }

    public ShipOperationDto getShipOperationOverview(UUID shipId) {
        logger.info("Fetching ship operation overview for ship with id: {}", shipId);
        List<BunkerOperationDto> bunkerOperations = bunkerOperationRepository.findByShipId(shipId)
                .stream()
                .map(this::toBunkerOperationDto)
                .collect(Collectors.toList());

        List<InspectionOperationDto> inspectionOperations = inspectionOperationRepository.findByDockOperation_ShipId(shipId)
                .stream()
                .map(this::toInspectionOperationDto)
                .collect(Collectors.toList());

        List<LoadingOperationDto> loadingOperations = loadingOperationRepository.findByShipId(shipId)
                .stream()
                .map(this::toLoadingOperationDto)
                .collect(Collectors.toList());

        if (bunkerOperations.isEmpty()) {
            logger.error("Bunker operations are not finished for shipId: {}", shipId);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bunker operations are not finished. Leaving early is not legally permitted");
        }

        if (inspectionOperations.isEmpty()) {
            logger.error("Inspection operations are not finished for shipId: {}", shipId);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Inspection operations are not finished. Leaving early is not legally permitted");
        }

        if (loadingOperations.isEmpty()) {
            logger.error("Loading operations are not finished for shipId: {}", shipId);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Loading operations are not finished. Leaving early is not legally permitted");
        }

        return new ShipOperationDto(bunkerOperations, inspectionOperations, loadingOperations);
    }

    public ShipOperationDto getShipOperationOverviewByVesselNumber(String vesselNumber){
        Ship ship = shipRepository.findByVesselNumber(vesselNumber)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ship with vesselNumber: " + vesselNumber + " not found"));

        return getShipOperationOverview(ship.getId());
    }

    private BunkerOperationDto toBunkerOperationDto(BunkerOperation bunkerOperation) {
        return new BunkerOperationDto(
                bunkerOperation.getId(),
                bunkerOperation.getOperationDate().toString(),
                bunkerOperation.getShip().getId()
        );
    }

    private InspectionOperationDto toInspectionOperationDto(InspectionOperation inspectionOperation) {
        return new InspectionOperationDto(
                inspectionOperation.getId(),
                inspectionOperation.getInspectionTime(),
                inspectionOperation.getInspectionNumber(),
                inspectionOperation.getDockOperation().getId(),
                inspectionOperation.isOpen(),
                inspectionOperation.isDone()
        );
    }

    private LoadingOperationDto toLoadingOperationDto(LoadingOperation loadingOperation) {
        return new LoadingOperationDto(
                loadingOperation.getId(),
                loadingOperation.getCargoType(),
                loadingOperation.getQuantity(),
                loadingOperation.getLoadingTime()
        );
    }
}
