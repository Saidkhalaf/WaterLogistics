package be.kdg.sa.water.controller.dto;

import be.kdg.sa.water.domain.PurchaseOrderStatus;

import java.util.List;

public record ProcessPurchaseOrderResponseDto(
        String message,
        String orderNumber,
        String status,
        List<PurchaseOrderStatus> statuses
) {
}
