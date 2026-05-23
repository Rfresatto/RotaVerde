package br.com.rota_verde.rota_verde.notifications.controller;

import br.com.rota_verde.rota_verde.notifications.dto.CreateNotificationDTO;
import br.com.rota_verde.rota_verde.notifications.dto.NotificationDTO;
import br.com.rota_verde.rota_verde.notifications.model.NotificationModel;
import br.com.rota_verde.rota_verde.notifications.service.NotificationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @PostMapping("/notification")
    public ResponseEntity<NotificationDTO> save(@RequestBody @Valid CreateNotificationDTO dto) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(notificationService.saveNotification(dto));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/notifications")
    public ResponseEntity<List<NotificationDTO>> findMany() {
        try {
            return ResponseEntity.ok(notificationService.findManyNotifications());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/notification/{id}")
    public ResponseEntity<NotificationDTO> find(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(notificationService.findNotification(id));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/notification/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            notificationService.deleteNotification(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/notification/{id}")
    public ResponseEntity<NotificationDTO> update(@PathVariable Long id, @RequestBody @Valid CreateNotificationDTO dto) {
        try {
            return ResponseEntity.ok(notificationService.updateNotification(id, dto));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}