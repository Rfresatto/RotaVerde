package br.com.rota_verde.rota_verde.containers.controller;

import br.com.rota_verde.rota_verde.containers.dto.ContainerDTO;
import br.com.rota_verde.rota_verde.containers.dto.CreateContainerDTO;
import br.com.rota_verde.rota_verde.containers.service.ContainerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ContainerController {

    @Autowired
    private ContainerService containerService;

    @PostMapping("/container")
    public ResponseEntity<ContainerDTO> save(@RequestBody @Valid CreateContainerDTO dto) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(containerService.saveContainer(dto));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/containers")
    public ResponseEntity<List<ContainerDTO>> findMany() {
        try {
            return ResponseEntity.ok(containerService.findManyContainers());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/container/{id}")
    public ResponseEntity<ContainerDTO> find(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(containerService.findContainer(id));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/container/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            containerService.deleteContainer(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/container/{id}")
    public ResponseEntity<ContainerDTO> update(@PathVariable Long id, @RequestBody @Valid CreateContainerDTO dto) {
        try {
            return ResponseEntity.ok(containerService.updateContainer(id, dto));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/containers/waste-type/{wasteType}")
    public ResponseEntity<List<ContainerDTO>> findByWasteType(@PathVariable String wasteType) {
        try {
            return ResponseEntity.ok(containerService.findByWasteType(wasteType));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/containers/critical")
    public ResponseEntity<List<ContainerDTO>> findByCriticalUsage() {
        try {
            return ResponseEntity.ok(containerService.findByCriticalUsage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/containers/collection-point/{pointId}")
    public ResponseEntity<List<ContainerDTO>> findByCollectionPoint(@PathVariable Long pointId) {
        try {
            return ResponseEntity.ok(containerService.findByCollectionPoint(pointId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/containers/empty")
    public ResponseEntity<List<ContainerDTO>> findEmptyContainers() {
        try {
            return ResponseEntity.ok(containerService.findEmptyContainers());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/containers/capacity")
    public ResponseEntity<List<ContainerDTO>> findByCapacityRange(
            @RequestParam BigDecimal min,
            @RequestParam BigDecimal max) {
        try {
            return ResponseEntity.ok(containerService.findByCapacityRange(min, max));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}