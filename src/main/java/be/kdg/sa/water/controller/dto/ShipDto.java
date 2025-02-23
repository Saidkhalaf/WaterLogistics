package be.kdg.sa.water.controller.dto;

import java.util.UUID;

public record ShipDto(
        String vesselNumber,
        UUID captainId
) {
}
