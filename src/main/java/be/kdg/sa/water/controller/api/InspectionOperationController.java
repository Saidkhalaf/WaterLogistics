package be.kdg.sa.water.controller.api;

import be.kdg.sa.water.controller.dto.InspectionOperationDto;
import be.kdg.sa.water.service.InspectionOperationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/inspection-operations")
public class InspectionOperationController {

    private final InspectionOperationService inspectionOperationService;

    public InspectionOperationController(InspectionOperationService inspectionOperationService) {
        this.inspectionOperationService = inspectionOperationService;
    }

    @GetMapping("/open")
    public ResponseEntity<List<InspectionOperationDto>> getOpenInspections() {
        List<InspectionOperationDto> openInspections = inspectionOperationService.getOpenInspections().stream()
                .map(inspectionOperationService::convertToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(openInspections);
    }

    @GetMapping("/done")
    public ResponseEntity<List<InspectionOperationDto>> getFullInspections() {
        List<InspectionOperationDto> fullInspections = inspectionOperationService.getFullInspections().stream()
                .map(inspectionOperationService::convertToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(fullInspections);
    }

    @GetMapping
    public List<InspectionOperationDto> getAllInspectionOperations() {
        return inspectionOperationService.getAllInspectionOperations();
    }

    @GetMapping("/{id}")
    public InspectionOperationDto getInspectionOperationById(@PathVariable UUID id) {
        return inspectionOperationService.getInspectionOperationById(id);
    }

    @PostMapping
    public InspectionOperationDto createInspectionOperation(@RequestBody InspectionOperationDto InspectionOperationDto) {
        return inspectionOperationService.createInspectionOperation(InspectionOperationDto);
    }

    @PutMapping("/{id}")
    public InspectionOperationDto updateInspectionOperation(@PathVariable UUID id, @RequestBody InspectionOperationDto InspectionOperationDto) {
        return inspectionOperationService.updateInspectionOperation(id, InspectionOperationDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInspectionOperation(@PathVariable UUID id) {
        inspectionOperationService.deleteInspectionOperation(id);
        return ResponseEntity.noContent().build();
    }
}
