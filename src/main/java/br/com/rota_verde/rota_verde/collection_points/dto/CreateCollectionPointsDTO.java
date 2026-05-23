package br.com.rota_verde.rota_verde.collection_points.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record CreateCollectionPointsDTO(
        @NotBlank(message = "O nome é obrigatório")
        String name,
        @NotBlank(message = "O endereço é obrigatório")
        String address,
        @NotBlank(message = "A cidade é obrigatória")
        String city,
        @NotNull(message = "A capacidade é obrigatória")
        BigDecimal capacityKg,
        Character active
) {}