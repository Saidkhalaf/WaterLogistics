package be.kdg.sa.water.controller.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record InspectionOperationDto(
        UUID id,
        LocalDateTime inspectionTime,
        String inspectionNumber,
        UUID dockOperationId,
        boolean isOpen,
        boolean isDone
) {
}
