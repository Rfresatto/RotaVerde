package br.com.rota_verde.rota_verde.collection_points.controller;

import br.com.rota_verde.rota_verde.collection_points.dto.CollectionPointsDTO;
import br.com.rota_verde.rota_verde.collection_points.dto.CreateCollectionPointsDTO;
import br.com.rota_verde.rota_verde.collection_points.service.CollectionPointsService;
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
public class CollectionPointsController {

    @Autowired
    private CollectionPointsService collectionPointService;

    @PostMapping("/collection-point")
    public ResponseEntity<CollectionPointsDTO> save(@RequestBody @Valid CreateCollectionPointsDTO dto) {
            return ResponseEntity.status(HttpStatus.CREATED).body(collectionPointService.saveCollectionPoint(dto));
    }

    @GetMapping("/collection-points")
    public ResponseEntity<Page<CollectionPointsDTO>> findMany(
            @PageableDefault(size = 5, page = 0)
            Pageable pageable
    ) {
            return ResponseEntity.ok(collectionPointService.findManyCollectionPoints(pageable));
    }

    @GetMapping("/collection-point/{id}")
    public ResponseEntity<CollectionPointsDTO> find(@PathVariable Long id) {
            return ResponseEntity.ok(collectionPointService.findCollectionPoint(id));
    }

    @DeleteMapping("/collection-point/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
            collectionPointService.deleteCollectionPoint(id);
            return ResponseEntity.noContent().build();
    }

    @PutMapping("/collection-point/{id}")
    public ResponseEntity<CollectionPointsDTO> update(@PathVariable Long id, @RequestBody @Valid CreateCollectionPointsDTO dto) {
            return ResponseEntity.ok(collectionPointService.updateCollectionPoint(id, dto));
    }

    @GetMapping("/collection-points/large")
    public ResponseEntity<List<CollectionPointsDTO>> findLargePoints() {
            return ResponseEntity.ok(collectionPointService.findLargePoints());
    }

    @GetMapping("/collection-points/active")
    public ResponseEntity<List<CollectionPointsDTO>> findActivePoints() {
            return ResponseEntity.ok(collectionPointService.findActivePoints());
    }

    @GetMapping("/collection-points/city/{city}")
    public ResponseEntity<List<CollectionPointsDTO>> findByCity(@PathVariable String city) {
            return ResponseEntity.ok(collectionPointService.findByCity(city));
    }

    @GetMapping("/collection-points/capacity")
    public ResponseEntity<List<CollectionPointsDTO>> findByCapacityRange(
            @RequestParam BigDecimal min,
            @RequestParam BigDecimal max) {
            return ResponseEntity.ok(collectionPointService.findByCapacityRange(min, max));
    }

    @GetMapping("/collection-points/search")
    public ResponseEntity<List<CollectionPointsDTO>> findByNameContaining(@RequestParam String name) {
            return ResponseEntity.ok(collectionPointService.findByNameContaining(name));
    }
}