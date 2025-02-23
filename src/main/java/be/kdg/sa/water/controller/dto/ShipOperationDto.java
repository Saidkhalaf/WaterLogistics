package be.kdg.sa.water.controller.dto;

import java.util.List;

public record ShipOperationDto(
        List<BunkerOperationDto> bunkerOperations,
        List<InspectionOperationDto> inspectionOperations,
        List<LoadingOperationDto> loadingOperations
) {
}
