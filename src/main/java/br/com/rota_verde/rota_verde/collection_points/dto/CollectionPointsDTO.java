package br.com.rota_verde.rota_verde.collection_points.dto;

import br.com.rota_verde.rota_verde.collection_points.model.CollectionPointsModel;
import java.math.BigDecimal;

public record CollectionPointsDTO(
        Long pointId,
        String name,
        String address,
        String city,
        BigDecimal capacityKg,
        Character active
) {
    public CollectionPointsDTO(CollectionPointsModel model) {
        this(
                model.getPointId(),
                model.getName(),
                model.getAddress(),
                model.getCity(),
                model.getCapacityKg(),
                model.getActive()
        );
    }
}