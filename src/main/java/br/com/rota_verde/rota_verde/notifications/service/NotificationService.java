package br.com.rota_verde.rota_verde.notifications.service;

import br.com.rota_verde.rota_verde.alert.model.AlertModel;
import br.com.rota_verde.rota_verde.alert.repository.AlertRepository;
import br.com.rota_verde.rota_verde.notifications.dto.CreateNotificationDTO;
import br.com.rota_verde.rota_verde.notifications.dto.NotificationDTO;
import br.com.rota_verde.rota_verde.notifications.model.NotificationModel;
import br.com.rota_verde.rota_verde.notifications.repository.NotificationRepository;
import lombok.Getter;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Getter
@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private AlertRepository alertRepository;

    public NotificationDTO saveNotification(CreateNotificationDTO dto) {
        AlertModel alert = alertRepository.findById(dto.alertId())
                .orElseThrow(() -> new RuntimeException("Alerta não encontrado."));

        NotificationModel model = new NotificationModel();
        BeanUtils.copyProperties(dto, model);
        model.setAlert(alert);
        model.setSentAt(new Date()); // ← default SYSDATE
        return new NotificationDTO(notificationRepository.save(model));
    }

    public NotificationDTO findNotification(Long id) {
        return new NotificationDTO(notificationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Notificação não encontrada.")));
    }

    public List<NotificationDTO> findManyNotifications() {
        return notificationRepository.findAll().stream()
                .map(NotificationDTO::new).toList();
    }

    public void deleteNotification(Long id) {
        NotificationModel model = notificationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Notificação não encontrada."));
        notificationRepository.delete(model);
    }

    public NotificationDTO updateNotification(Long id, CreateNotificationDTO dto) {
        NotificationModel model = notificationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Notificação não encontrada."));
        AlertModel alert = alertRepository.findById(dto.alertId())
                .orElseThrow(() -> new RuntimeException("Alerta não encontrado."));
        BeanUtils.copyProperties(dto, model);
        model.setAlert(alert);
        return new NotificationDTO(notificationRepository.save(model));
    }
}