package br.com.rota_verde.rota_verde.alert.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateAlertDTO(
        @NotNull(message = "O container é obrigatório")
        Long containerId,
        @NotBlank(message = "O tipo do alerta é obrigatório")
        String alertType,
        @NotBlank(message = "A mensagem é obrigatória")
        String message,
        Character resolved
) {}