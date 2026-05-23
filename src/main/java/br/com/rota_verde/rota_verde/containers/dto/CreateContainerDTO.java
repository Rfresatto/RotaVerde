package br.com.rota_verde.rota_verde.containers.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record CreateContainerDTO(
        @NotNull(message = "O ponto de coleta é obrigatório")
        Long collectionPointId,
        @NotBlank(message = "O tipo de resíduo é obrigatório")
        String wasteType,
        @NotNull(message = "A capacidade é obrigatória")
        BigDecimal capacityKg,
        BigDecimal currentVolumeKg
) {}