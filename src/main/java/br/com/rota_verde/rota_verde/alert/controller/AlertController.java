package br.com.rota_verde.rota_verde.alert.controller;

import br.com.rota_verde.rota_verde.alert.dto.AlertDTO;
import br.com.rota_verde.rota_verde.alert.dto.CreateAlertDTO;
import br.com.rota_verde.rota_verde.alert.service.AlertService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api")
public class AlertController {

    @Autowired
    private AlertService alertService;

    @PostMapping("/alert")
    public ResponseEntity<AlertDTO> save(@RequestBody @Valid CreateAlertDTO dto) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(alertService.saveAlert(dto));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/alerts")
    public ResponseEntity<List<AlertDTO>> findMany() {
        try {
            return ResponseEntity.ok(alertService.findManyAlerts());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/alert/{id}")
    public ResponseEntity<AlertDTO> find(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(alertService.findAlert(id));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/alert/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            alertService.deleteAlert(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/alert/{id}")
    public ResponseEntity<AlertDTO> update(@PathVariable Long id, @RequestBody @Valid CreateAlertDTO dto) {
        try {
            return ResponseEntity.ok(alertService.updateAlert(id, dto));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/alerts/unresolved")
    public ResponseEntity<List<AlertDTO>> findUnresolvedAlerts() {
        try {
            return ResponseEntity.ok(alertService.findUnresolvedAlerts());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/alerts/type/{alertType}")
    public ResponseEntity<List<AlertDTO>> findByAlertType(@PathVariable String alertType) {
        try {
            return ResponseEntity.ok(alertService.findByAlertType(alertType));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/alerts/container/{containerId}")
    public ResponseEntity<List<AlertDTO>> findByContainer(@PathVariable Long containerId) {
        try {
            return ResponseEntity.ok(alertService.findByContainer(containerId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/alerts/date")
    public ResponseEntity<List<AlertDTO>> findByDateRange(
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate) {
        try {
            return ResponseEntity.ok(alertService.findByDateRange(startDate, endDate));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/alerts/resolved")
    public ResponseEntity<List<AlertDTO>> findResolvedAlerts() {
        try {
            return ResponseEntity.ok(alertService.findResolvedAlerts());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}