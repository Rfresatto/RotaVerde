package br.com.rota_verde.rota_verde.notifications.controller;

import br.com.rota_verde.rota_verde.notifications.dto.CreateNotificationDTO;
import br.com.rota_verde.rota_verde.notifications.dto.NotificationDTO;
import br.com.rota_verde.rota_verde.notifications.service.NotificationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @PostMapping("/notification")
    public ResponseEntity<NotificationDTO> save(@RequestBody @Valid CreateNotificationDTO dto) {
            return ResponseEntity.status(HttpStatus.CREATED).body(notificationService.saveNotification(dto));
    }

    @GetMapping("/notifications")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Page<NotificationDTO>> findMany(
            @PageableDefault(size = 5, page = 0) Pageable pageable
    ) {
            return ResponseEntity.ok(notificationService.findManyNotifications(pageable));
    }

    @GetMapping("/notification/{id}")
    public ResponseEntity<NotificationDTO> find(@PathVariable Long id) {
            return ResponseEntity.ok(notificationService.findNotification(id));
    }

    @DeleteMapping("/notification/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
            notificationService.deleteNotification(id);
            return ResponseEntity.noContent().build();
    }

    @PutMapping("/notification/{id}")
    public ResponseEntity<NotificationDTO> update(@PathVariable Long id, @RequestBody @Valid CreateNotificationDTO dto) {
            return ResponseEntity.ok(notificationService.updateNotification(id, dto));
    }

    @GetMapping("/notifications/unsent")
    public ResponseEntity<List<NotificationDTO>> findUnsentNotifications() {
            return ResponseEntity.ok(notificationService.findUnsentNotifications());
    }

    @GetMapping("/notifications/channel/{channel}")
    public ResponseEntity<List<NotificationDTO>> findByChannel(@PathVariable String channel) {
            return ResponseEntity.ok(notificationService.findByChannel(channel));
    }

    @GetMapping("/notifications/alert/{alertId}")
    public ResponseEntity<List<NotificationDTO>> findByAlert(@PathVariable Long alertId) {
            return ResponseEntity.ok(notificationService.findByAlert(alertId));
    }

    @GetMapping("/notifications/recipient/{recipient}")
    public ResponseEntity<List<NotificationDTO>> findByRecipient(@PathVariable String recipient) {
            return ResponseEntity.ok(notificationService.findByRecipient(recipient));
    }

    @GetMapping("/notifications/date")
    public ResponseEntity<List<NotificationDTO>> findByDateRange(
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate) {
            return ResponseEntity.ok(notificationService.findByDateRange(startDate, endDate));
    }
}