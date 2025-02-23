package be.kdg.sa.water.service;

import be.kdg.sa.water.controller.dto.PurchaseOrderDto;
import be.kdg.sa.water.domain.PurchaseOrder;
import be.kdg.sa.water.domain.PurchaseOrderStatus;
import be.kdg.sa.water.domain.Ship;
import be.kdg.sa.water.repository.PurchaseOrderRepository;
import be.kdg.sa.water.repository.ShipRepository;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PurchaseOrderService {

    private final Logger logger = org.slf4j.LoggerFactory.getLogger(PurchaseOrderService.class);
    private final PurchaseOrderRepository purchaseOrderRepository;
    private final ShipRepository shipRepository;

    public PurchaseOrderService(PurchaseOrderRepository purchaseOrderRepository, ShipRepository shipRepository) {
        this.purchaseOrderRepository = purchaseOrderRepository;
        this.shipRepository = shipRepository;
    }

    public PurchaseOrder processPurchaseOrder(PurchaseOrderDto purchaseOrderDto) {
        logger.info("Processing purchase order with order number: {}", purchaseOrderDto.orderNumber());
        Ship ship = shipRepository.findById(purchaseOrderDto.shipId())
                .orElseThrow(() -> new RuntimeException("Ship not found"));
        PurchaseOrder purchaseOrder = new PurchaseOrder();
        purchaseOrder.setOrderNumber(purchaseOrderDto.orderNumber());
        purchaseOrder.setShip(ship);
        purchaseOrder.setStatus(new ArrayList<>());
        purchaseOrderRepository.save(purchaseOrder);

        //Logic to initiate inspection, bunker, and loading of the ship
        initiateInspection(purchaseOrder);
        initiateBunkering(purchaseOrder);
        initiateLoading(purchaseOrder);

        return purchaseOrder;
    }

    private void initiateInspection(PurchaseOrder purchaseOrder) {
        addStatus(purchaseOrder, PurchaseOrderStatus.INSPECTION_INITIATED);
    }

    private void initiateBunkering(PurchaseOrder purchaseOrder) {
        addStatus(purchaseOrder, PurchaseOrderStatus.BUNKERING_INITIATED);
    }

    private void initiateLoading(PurchaseOrder purchaseOrder) {
        addStatus(purchaseOrder, PurchaseOrderStatus.LOADING_INITIATED);
    }

    private void addStatus(PurchaseOrder purchaseOrder, PurchaseOrderStatus status) {
        purchaseOrder.getStatus().add(status);
        purchaseOrderRepository.save(purchaseOrder);
    }

    public List<PurchaseOrderDto> getAllPurchaseOrders() {
        return purchaseOrderRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public PurchaseOrderDto getPurchaseOrderById(UUID id) {
        return purchaseOrderRepository.findById(id)
                .map(this::convertToDto)
                .orElseThrow(() -> new RuntimeException("PurchaseOrder not found"));
    }

    public PurchaseOrderDto createPurchaseOrder(PurchaseOrderDto PurchaseOrderDto) {
        PurchaseOrder purchaseOrder = convertToEntity(PurchaseOrderDto);
        purchaseOrder = purchaseOrderRepository.save(purchaseOrder);
        return convertToDto(purchaseOrder);
    }

    public PurchaseOrderDto updatePurchaseOrder(UUID id, PurchaseOrderDto PurchaseOrderDto) {
        PurchaseOrder purchaseOrder = convertToEntity(PurchaseOrderDto);
        purchaseOrder.setId(id);
        purchaseOrder = purchaseOrderRepository.save(purchaseOrder);
        return convertToDto(purchaseOrder);
    }

    public void deletePurchaseOrder(UUID id) {
        purchaseOrderRepository.deleteById(id);
    }

    private PurchaseOrderDto convertToDto(PurchaseOrder purchaseOrder) {
        return new PurchaseOrderDto(
                purchaseOrder.getOrderNumber(),
                purchaseOrder.getShip().getId()
        );
    }

    private PurchaseOrder convertToEntity(PurchaseOrderDto dto) {
        PurchaseOrder purchaseOrder = new PurchaseOrder();
        purchaseOrder.setOrderNumber(dto.orderNumber());
        // Set other fields and relationships
        return purchaseOrder;
    }
}
