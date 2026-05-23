package br.com.rota_verde.rota_verde.notifications.repository;

import br.com.rota_verde.rota_verde.notifications.model.NotificationModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<NotificationModel, Long> {
}
