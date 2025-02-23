package be.kdg.sa.water.controller.api;

import be.kdg.sa.water.controller.dto.ProcessPurchaseOrderResponseDto;
import be.kdg.sa.water.controller.dto.PurchaseOrderDto;
import be.kdg.sa.water.domain.PurchaseOrder;
import be.kdg.sa.water.service.PurchaseOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/purchase-orders")
public class PurchaseOrderController {

    private final Logger logger = LoggerFactory.getLogger(PurchaseOrderController.class);
    private final PurchaseOrderService purchaseOrderService;

    public PurchaseOrderController(PurchaseOrderService purchaseOrderService) {
        this.purchaseOrderService = purchaseOrderService;
    }

    @PostMapping("/process")
    public ResponseEntity<ProcessPurchaseOrderResponseDto> processPurchaseOrder(@RequestBody PurchaseOrderDto purchaseOrderDto) {
        logger.info("Processing purchase order with order number: {}", purchaseOrderDto.orderNumber());
        PurchaseOrder purchaseOrder = purchaseOrderService.processPurchaseOrder(purchaseOrderDto);
        ProcessPurchaseOrderResponseDto response = new ProcessPurchaseOrderResponseDto(
                "Purchase order processed successfully",
                purchaseOrderDto.orderNumber(),
                "Processing",
                purchaseOrder.getStatus()
        );
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public List<PurchaseOrderDto> getAllPurchaseOrders() {
        return purchaseOrderService.getAllPurchaseOrders();
    }

    @GetMapping("/{id}")
    public PurchaseOrderDto getPurchaseOrderById(@PathVariable UUID id) {
        return purchaseOrderService.getPurchaseOrderById(id);
    }

    @PostMapping
    public PurchaseOrderDto createPurchaseOrder(@RequestBody PurchaseOrderDto PurchaseOrderDto) {
        return purchaseOrderService.createPurchaseOrder(PurchaseOrderDto);
    }

    @PutMapping("/{id}")
    public PurchaseOrderDto updatePurchaseOrder(@PathVariable UUID id, @RequestBody PurchaseOrderDto PurchaseOrderDto) {
        return purchaseOrderService.updatePurchaseOrder(id, PurchaseOrderDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePurchaseOrder(@PathVariable UUID id) {
        purchaseOrderService.deletePurchaseOrder(id);
        return ResponseEntity.noContent().build();
    }
}
