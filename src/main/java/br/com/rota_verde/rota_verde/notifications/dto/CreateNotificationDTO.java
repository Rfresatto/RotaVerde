package br.com.rota_verde.rota_verde.notifications.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateNotificationDTO(
        @NotNull(message = "O alerta é obrigatório")
        Long alertId,
        @NotBlank(message = "O destinatário é obrigatório")
        String recipient,
        @NotBlank(message = "O canal é obrigatório")
        String channel,
        @NotBlank(message = "A mensagem é obrigatória")
        String message,
        Character sent
) {
}
