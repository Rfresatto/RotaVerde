package br.com.rota_verde.rota_verde.alert.dto;

import br.com.rota_verde.rota_verde.alert.model.AlertModel;
import java.time.LocalDate;

public record AlertDTO(
        Long idAlert,
        Long containerId,
        String alertType,
        String message,
        Character resolved,
        LocalDate resolvedAt
) {
    public AlertDTO(AlertModel model) {
        this(
                model.getIdAlert(),
                model.getContainer().getContainerId(),
                model.getAlertType(),
                model.getMessage(),
                model.getResolved(),
                model.getResolvedAt()
        );
    }
}