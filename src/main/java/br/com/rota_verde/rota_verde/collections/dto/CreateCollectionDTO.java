package br.com.rota_verde.rota_verde.collections.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.Date;

public record CreateCollectionDTO(
        @NotNull(message = "O container é obrigatório")
        Long containerId,
        @NotNull(message = "A data de coleta é obrigatória")
        Date collectionDate,
        @NotNull(message = "O peso coletado é obrigatório")
        BigDecimal collectedWeightKg,
        @NotBlank(message = "O responsável é obrigatório")
        String responsible,
        @NotBlank(message = "O destino é obrigatório")
        String destination,
        String notes
) {}
