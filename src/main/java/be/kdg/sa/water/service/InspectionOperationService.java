package be.kdg.sa.water.service;

import be.kdg.sa.water.controller.dto.InspectionOperationDto;
import be.kdg.sa.water.domain.InspectionOperation;
import be.kdg.sa.water.repository.InspectionOperationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class InspectionOperationService {

    private final Logger logger = LoggerFactory.getLogger(InspectionOperationService.class);
    private final InspectionOperationRepository inspectionOperationRepository;

    public InspectionOperationService(InspectionOperationRepository inspectionOperationRepository) {
        this.inspectionOperationRepository = inspectionOperationRepository;
    }

    public List<InspectionOperation> getOpenInspections() {
        return inspectionOperationRepository.findInspectionOperationsByIsOpenTrue();
    }

    public List<InspectionOperation> getFullInspections() {
        return inspectionOperationRepository.findInspectionOperationsByIsDoneTrue();
    }


    public List<InspectionOperationDto> getAllInspectionOperations() {
        return inspectionOperationRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public InspectionOperationDto getInspectionOperationById(UUID id) {
        logger.info("Fetching inspection operation with id: {}", id);
        return inspectionOperationRepository.findById(id)
                .map(this::convertToDto)
                .orElseThrow(() -> new RuntimeException("InspectionOperation not found"));
    }

    public InspectionOperationDto createInspectionOperation(InspectionOperationDto InspectionOperationDto) {
        InspectionOperation inspectionOperation = convertToEntity(InspectionOperationDto);
        inspectionOperation = inspectionOperationRepository.save(inspectionOperation);
        return convertToDto(inspectionOperation);
    }

    public InspectionOperationDto updateInspectionOperation(UUID id, InspectionOperationDto InspectionOperationDto) {
        InspectionOperation inspectionOperation = convertToEntity(InspectionOperationDto);
        inspectionOperation.setId(id);
        inspectionOperation = inspectionOperationRepository.save(inspectionOperation);
        return convertToDto(inspectionOperation);
    }

    public void deleteInspectionOperation(UUID id) {
        inspectionOperationRepository.deleteById(id);
    }

    public InspectionOperationDto convertToDto(InspectionOperation inspectionOperation) {
        return new InspectionOperationDto(
                inspectionOperation.getId(),
                inspectionOperation.getInspectionTime(),
                inspectionOperation.getInspectionNumber(),
                inspectionOperation.getDockOperation().getId(),
                inspectionOperation.isOpen(),
                inspectionOperation.isDone()
        );
    }

    public InspectionOperation convertToEntity(InspectionOperationDto dto) {
        InspectionOperation inspectionOperation = new InspectionOperation();
        inspectionOperation.setInspectionTime(dto.inspectionTime());
        inspectionOperation.setInspectionNumber(dto.inspectionNumber());
        // Set other fields and relationships
        return inspectionOperation;
    }
}
