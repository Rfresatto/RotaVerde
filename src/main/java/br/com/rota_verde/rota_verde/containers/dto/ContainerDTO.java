package br.com.rota_verde.rota_verde.containers.dto;

import br.com.rota_verde.rota_verde.containers.model.ContainerModel;
import java.math.BigDecimal;

public record ContainerDTO(
        Long containerId,
        Long collectionPointId,
        String wasteType,
        BigDecimal capacityKg,
        BigDecimal currentVolumeKg,
        BigDecimal usagePercentage
) {
    public ContainerDTO(ContainerModel model) {
        this(
                model.getContainerId(),
                model.getCollectionPoint().getPointId(),
                model.getWasteType(),
                model.getCapacityKg(),
                model.getCurrentVolumeKg(),
                model.getUsagePercentage()
        );
    }
}