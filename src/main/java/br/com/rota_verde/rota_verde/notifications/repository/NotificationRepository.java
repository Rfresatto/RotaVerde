package br.com.rota_verde.rota_verde.notifications.repository;

import br.com.rota_verde.rota_verde.notifications.model.NotificationModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface NotificationRepository extends JpaRepository<NotificationModel, Long> {

    @Query("SELECT n FROM NotificationModel n WHERE n.sent = 'N'")
    List<NotificationModel> findUnsentNotifications();

    @Query("SELECT n FROM NotificationModel n WHERE LOWER(n.channel) LIKE LOWER(CONCAT('%',:channel,'%'))")
    List<NotificationModel> findByChannel(@Param("channel") String channel);

    @Query("SELECT n FROM NotificationModel n WHERE n.alert.idAlert = :alertId")
    List<NotificationModel> findByAlert(@Param("alertId") Long alertId);

    @Query("SELECT n FROM NotificationModel n WHERE LOWER(n.recipient) LIKE LOWER(CONCAT('%',:recipient,'%'))")
    List<NotificationModel> findByRecipient(@Param("recipient") String recipient);

    @Query("SELECT n FROM NotificationModel n WHERE n.sentAt BETWEEN :startDate AND :endDate")
    List<NotificationModel> findByDateRange(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
}
