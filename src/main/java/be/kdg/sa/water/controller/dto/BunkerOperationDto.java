package be.kdg.sa.water.controller.dto;

import java.util.UUID;

public record BunkerOperationDto(
        UUID id,
        String vesselNumber,
        UUID shipId
) {
}
