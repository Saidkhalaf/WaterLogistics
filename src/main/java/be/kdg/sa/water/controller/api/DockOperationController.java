package be.kdg.sa.water.controller.api;

import be.kdg.sa.water.controller.dto.DockOperationDto;
import be.kdg.sa.water.service.DockOperationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/dock-operations")
public class DockOperationController {

    private final Logger logger = LoggerFactory.getLogger(DockOperationController.class);
    private final DockOperationService dockOperationService;

    public DockOperationController(DockOperationService dockOperationService) {
        this.dockOperationService = dockOperationService;
    }

    @GetMapping
    public List<DockOperationDto> getAllDockOperations(){
        return dockOperationService.getAllDockOperations();
    }

    @GetMapping("/{id}")
    public DockOperationDto getDockOperationById(@PathVariable UUID id){
        return dockOperationService.getDockOperationById(id);
    }

    @PostMapping
    public DockOperationDto createDockOperation(@RequestBody DockOperationDto dockOperationDto){
        return dockOperationService.createDockOperation(dockOperationDto);
    }

    @PutMapping("/{id}")
    public DockOperationDto updateDockOperation(@PathVariable UUID id, @RequestBody DockOperationDto dockOperationDto){
        return dockOperationService.updateDockOperation(id, dockOperationDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDockOperation(@PathVariable UUID id){
        logger.info("Received request to delete dock operation with id: {}", id);
        dockOperationService.deleteDockOperation(id);
        return ResponseEntity.noContent().build();
    }

}
