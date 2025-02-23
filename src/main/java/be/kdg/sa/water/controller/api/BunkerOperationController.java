package be.kdg.sa.water.controller.api;

import be.kdg.sa.water.controller.dto.BunkerOperationDto;
import be.kdg.sa.water.domain.BunkerOperation;
import be.kdg.sa.water.service.BunkerOperationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/bunker-operations")
public class BunkerOperationController {

    private final Logger logger = LoggerFactory.getLogger(BunkerOperationController.class);
    private final BunkerOperationService bunkerOperationService;

    public BunkerOperationController(BunkerOperationService bunkerOperationService) {
        this.bunkerOperationService = bunkerOperationService;
    }

    @PostMapping("/plan")
    public ResponseEntity<String> planBunkerOperation(@RequestBody BunkerOperation bunkerOperation) {
        try {
            boolean success = bunkerOperationService.planBunkerOperation(bunkerOperation);
            if (success) {
                logger.info("Bunker operation planned successfully");
                return ResponseEntity.ok("Bunker operation planned successfully");
            } else {
                logger.error("Maximum number of bunker operations per day reached");
                return ResponseEntity.badRequest().body("Maximum number of bunker operations per day reached");
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public List<BunkerOperationDto> getAllBunkerOperations() {
        return bunkerOperationService.getAllBunkerOperations();
    }

    @GetMapping("/{id}")
    public BunkerOperationDto getBunkerOperationById(@PathVariable UUID id) {
        return bunkerOperationService.getBunkerOperationById(id);
    }

    @PostMapping
    public BunkerOperationDto createBunkerOperation(@RequestBody BunkerOperationDto BunkerOperationDto) {
        return bunkerOperationService.createBunkerOperation(BunkerOperationDto);
    }

    @PutMapping("/{id}")
    public BunkerOperationDto updateBunkerOperation(@PathVariable UUID id, @RequestBody BunkerOperationDto BunkerOperationDto) {
        return bunkerOperationService.updateBunkerOperation(id, BunkerOperationDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBunkerOperation(@PathVariable UUID id) {
        bunkerOperationService.deleteBunkerOperation(id);
        return ResponseEntity.noContent().build();
    }
}
