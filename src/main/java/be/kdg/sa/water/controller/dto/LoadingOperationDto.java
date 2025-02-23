package be.kdg.sa.water.controller.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record LoadingOperationDto(
        UUID id,
        String cargoType,
        double quantity,
        LocalDateTime loadingTime
) {
}
