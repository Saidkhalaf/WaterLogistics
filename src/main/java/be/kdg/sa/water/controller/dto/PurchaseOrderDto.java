package be.kdg.sa.water.controller.dto;

import java.util.UUID;

public record PurchaseOrderDto(
        String orderNumber,
        UUID shipId
) {
}
