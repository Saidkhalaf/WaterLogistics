package be.kdg.sa.water.controller.api;

import be.kdg.sa.water.controller.dto.ShipDto;
import be.kdg.sa.water.service.ShipService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/ships")
public class ShipController {

    private final ShipService shipService;

    public ShipController(ShipService shipService) {
        this.shipService = shipService;
    }

    @GetMapping
    public List<ShipDto> getAllShips() {
        return shipService.getAllShips();
    }

    @GetMapping("/{id}")
    public ShipDto getShipById(@PathVariable UUID id) {
        return shipService.getShipById(id);
    }

    @PostMapping
    public ShipDto createShip(@RequestBody ShipDto ShipDto) {
        return shipService.createShip(ShipDto);
    }

    @PutMapping("/{id}")
    public ShipDto updateShip(@PathVariable UUID id, @RequestBody ShipDto ShipDto) {
        return shipService.updateShip(id, ShipDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteShip(@PathVariable UUID id) {
        shipService.deleteShip(id);
        return ResponseEntity.noContent().build();
    }
}
