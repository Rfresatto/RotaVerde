package br.com.rota_verde.rota_verde.containers.controller;

import br.com.rota_verde.rota_verde.containers.dto.ContainerDTO;
import br.com.rota_verde.rota_verde.containers.dto.CreateContainerDTO;
import br.com.rota_verde.rota_verde.containers.service.ContainerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
        return ResponseEntity.status(HttpStatus.CREATED).body(containerService.saveContainer(dto));
    }

    @GetMapping("/containers")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Page<ContainerDTO>> findMany(
            @PageableDefault
            Pageable pageable
    ) {
        return ResponseEntity.ok(containerService.findManyContainers(pageable));
    }

    @GetMapping("/container/{id}")
    public ResponseEntity<ContainerDTO> find(@PathVariable Long id) {
        return ResponseEntity.ok(containerService.findContainer(id));
    }

    @DeleteMapping("/container/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        containerService.deleteContainer(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/container/{id}")
    public ResponseEntity<ContainerDTO> update(@PathVariable Long id, @RequestBody @Valid CreateContainerDTO dto) {
        return ResponseEntity.ok(containerService.updateContainer(id, dto));
    }

    @GetMapping("/containers/waste-type/{wasteType}")
    public ResponseEntity<List<ContainerDTO>> findByWasteType(@PathVariable String wasteType) {
        return ResponseEntity.ok(containerService.findByWasteType(wasteType));
    }

    @GetMapping("/containers/critical")
    public ResponseEntity<List<ContainerDTO>> findByCriticalUsage() {
        return ResponseEntity.ok(containerService.findByCriticalUsage());
    }

    @GetMapping("/containers/collection-point/{pointId}")
    public ResponseEntity<List<ContainerDTO>> findByCollectionPoint(@PathVariable Long pointId) {
        return ResponseEntity.ok(containerService.findByCollectionPoint(pointId));
    }

    @GetMapping("/containers/empty")
    public ResponseEntity<List<ContainerDTO>> findEmptyContainers() {
        return ResponseEntity.ok(containerService.findEmptyContainers());
    }

    @GetMapping("/containers/capacity")
    public ResponseEntity<List<ContainerDTO>> findByCapacityRange(
            @RequestParam BigDecimal min,
            @RequestParam BigDecimal max) {
        return ResponseEntity.ok(containerService.findByCapacityRange(min, max));
    }
}