package be.kdg.sa.water.service;

import be.kdg.sa.water.controller.dto.DockOperationDto;
import be.kdg.sa.water.domain.DockOperation;
import be.kdg.sa.water.repository.DockOperationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class DockOperationService {

    private final Logger logger = LoggerFactory.getLogger(DockOperationService.class);
    private final DockOperationRepository dockOperationRepository;

    public DockOperationService(DockOperationRepository dockOperationRepository) {
        this.dockOperationRepository = dockOperationRepository;
    }

    public List<DockOperationDto> getAllDockOperations() {
        return dockOperationRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public DockOperationDto getDockOperationById(UUID id) {
        logger.info("Fetching dock operation with id: {}", id);
        return dockOperationRepository.findById(id)
                .map(this::convertToDTO)
                .orElseThrow(() -> new RuntimeException("DockOperation not found"));
    }

    public DockOperationDto createDockOperation(DockOperationDto dockOperationDTO) {
        DockOperation dockOperation = convertToEntity(dockOperationDTO);
        dockOperation = dockOperationRepository.save(dockOperation);
        return convertToDTO(dockOperation);
    }

    public DockOperationDto updateDockOperation(UUID id, DockOperationDto dockOperationDTO) {
        DockOperation dockOperation = convertToEntity(dockOperationDTO);
        dockOperation.setId(id);
        dockOperation = dockOperationRepository.save(dockOperation);
        return convertToDTO(dockOperation);
    }

    public void deleteDockOperation(UUID id) {
        dockOperationRepository.deleteById(id);
    }

    private DockOperationDto convertToDTO(DockOperation dockOperation) {
        return new DockOperationDto(
                dockOperation.getId(),
                dockOperation.getVesselNumber(),
                dockOperation.getShipArrivalTime(),
//                dockOperation.getShipDepartureTime(),
                dockOperation.getPurchaseOrder().getId(),
                dockOperation.getInspectionOperation().getId()
        );
    }

    private DockOperation convertToEntity(DockOperationDto dto) {
        DockOperation dockOperation = new DockOperation();
        dockOperation.setVesselNumber(dto.vesselNumber());
        dockOperation.setShipArrivalTime(dto.shipArrivalTime());
//        dockOperation.setShipDepartureTime(dto.shipDepartureTime());
        // Set other fields and relationships
        return dockOperation;
    }
}
