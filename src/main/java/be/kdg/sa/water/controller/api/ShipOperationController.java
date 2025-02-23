package be.kdg.sa.water.controller.api;

import be.kdg.sa.water.controller.dto.ShipOperationDto;
import be.kdg.sa.water.service.ShipOperationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/ship-operations")
public class ShipOperationController {

    private final Logger logger = LoggerFactory.getLogger(ShipOperationController.class);

    private final ShipOperationService shipOperationService;

    public ShipOperationController(ShipOperationService shipOperationService) {
        this.shipOperationService = shipOperationService;
    }

    @GetMapping("/{shipId}/operations-overview")
    public ResponseEntity<ShipOperationDto> getShipOperationOverview(@PathVariable UUID shipId) {
        logger.info("Received request for ship operation overview for shipId: {}", shipId);
        return ResponseEntity.ok(shipOperationService.getShipOperationOverview(shipId));
    }

    @PostMapping("/operations-overview/{vesselNumber}")
    public ResponseEntity<Map<String, Object>> getShipOperationOverviewByVesselNumber(@PathVariable String vesselNumber) {
        logger.info("Received request for ship operation overview for vesselNumber: {}", vesselNumber);
        ShipOperationDto shipOperationDto = shipOperationService.getShipOperationOverviewByVesselNumber(vesselNumber);
        String message = "All operations are implemented. The captain may leave.";
        return ResponseEntity.ok(Map.of("message", message, "shipOperationDto", shipOperationDto));
    }
}
