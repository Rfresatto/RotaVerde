package br.com.rota_verde.rota_verde.notifications.dto;

import br.com.rota_verde.rota_verde.notifications.model.NotificationModel;

import java.time.LocalDate;

public record NotificationDTO(
        Long notificationId,
        Long alertId,
        String recipient,
        String channel,
        String message,
        LocalDate sentAt,
        Character sent
) {
    public NotificationDTO(NotificationModel model) {
        this(
                model.getNotificationId(),
                model.getAlert().getIdAlert(),
                model.getRecipient(),
                model.getChannel(),
                model.getMessage(),
                model.getSentAt(),
                model.getSent()
        );
    }
}