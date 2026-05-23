package br.com.rota_verde.rota_verde.collections.dto;

import br.com.rota_verde.rota_verde.collections.model.CollectionsModel;
import java.math.BigDecimal;
import java.time.LocalDate;

public record CollectionDTO(
        Long collectionId,
        Long containerId,
        LocalDate collectionDate,
        BigDecimal collectedWeightKg,
        String responsible,
        String destination,
        String notes
) {
    public CollectionDTO(CollectionsModel model) {
        this(
                model.getCollectionId(),
                model.getContainer().getContainerId(),
                model.getCollectionDate(),
                model.getCollectedWeightKg(),
                model.getResponsible(),
                model.getDestination(),
                model.getNotes()
        );
    }
}