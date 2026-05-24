package br.com.rota_verde.rota_verde.alert.controller;

import br.com.rota_verde.rota_verde.alert.dto.AlertDTO;
import br.com.rota_verde.rota_verde.alert.dto.CreateAlertDTO;
import br.com.rota_verde.rota_verde.alert.service.AlertService;
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
public class AlertController {

    @Autowired
    private AlertService alertService;

    @PostMapping("/alert")
    public ResponseEntity<AlertDTO> save(@RequestBody @Valid CreateAlertDTO dto) {
            return ResponseEntity.status(HttpStatus.CREATED).body(alertService.saveAlert(dto));
    }

    @GetMapping("/alerts")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Page<AlertDTO>> findMany(
            @PageableDefault(size = 5, page = 0)
            Pageable pageable
    ) {
            return ResponseEntity.ok(alertService.findManyAlerts(pageable));
    }

    @GetMapping("/alert/{id}")
    public ResponseEntity<AlertDTO> find(@PathVariable Long id) {
            return ResponseEntity.ok(alertService.findAlert(id));
    }

    @DeleteMapping("/alert/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
            alertService.deleteAlert(id);
            return ResponseEntity.noContent().build();
    }

    @PutMapping("/alert/{id}")
    public ResponseEntity<AlertDTO> update(@PathVariable Long id, @RequestBody @Valid CreateAlertDTO dto) {
            return ResponseEntity.ok(alertService.updateAlert(id, dto));
    }

    @GetMapping("/alerts/unresolved")
    public ResponseEntity<List<AlertDTO>> findUnresolvedAlerts() {
            return ResponseEntity.ok(alertService.findUnresolvedAlerts());
    }

    @GetMapping("/alerts/type/{alertType}")
    public ResponseEntity<List<AlertDTO>> findByAlertType(@PathVariable String alertType) {
            return ResponseEntity.ok(alertService.findByAlertType(alertType));
    }

    @GetMapping("/alerts/container/{containerId}")
    public ResponseEntity<List<AlertDTO>> findByContainer(@PathVariable Long containerId) {
            return ResponseEntity.ok(alertService.findByContainer(containerId));
    }

    @GetMapping("/alerts/date")
    public ResponseEntity<List<AlertDTO>> findByDateRange(
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate) {
            return ResponseEntity.ok(alertService.findByDateRange(startDate, endDate));
    }

    @GetMapping("/alerts/resolved")
    public ResponseEntity<List<AlertDTO>> findResolvedAlerts() {
            return ResponseEntity.ok(alertService.findResolvedAlerts());
    }
}