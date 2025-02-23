package be.kdg.sa.water.controller.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record DockOperationDto(UUID id,
                               String vesselNumber,
                               LocalDateTime shipArrivalTime,
                               UUID purchaseOrderId,
                               UUID inspectionOperationId
) {}
