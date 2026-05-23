package br.com.rota_verde.rota_verde.collection_points.controller;

import br.com.rota_verde.rota_verde.collection_points.dto.CollectionPointsDTO;
import br.com.rota_verde.rota_verde.collection_points.dto.CreateCollectionPointsDTO;
import br.com.rota_verde.rota_verde.collection_points.service.CollectionPointsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(collectionPointService.saveCollectionPoint(dto));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/collection-points")
    public ResponseEntity<List<CollectionPointsDTO>> findMany() {
        try {
            return ResponseEntity.ok(collectionPointService.findManyCollectionPoints());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/collection-point/{id}")
    public ResponseEntity<CollectionPointsDTO> find(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(collectionPointService.findCollectionPoint(id));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/collection-point/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            collectionPointService.deleteCollectionPoint(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/collection-point/{id}")
    public ResponseEntity<CollectionPointsDTO> update(@PathVariable Long id, @RequestBody @Valid CreateCollectionPointsDTO dto) {
        try {
            return ResponseEntity.ok(collectionPointService.updateCollectionPoint(id, dto));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/collection-points/large")
    public ResponseEntity<List<CollectionPointsDTO>> findLargePoints() {
        try {
            return ResponseEntity.ok(collectionPointService.findLargePoints());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/collection-points/active")
    public ResponseEntity<List<CollectionPointsDTO>> findActivePoints() {
        try {
            return ResponseEntity.ok(collectionPointService.findActivePoints());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/collection-points/city/{city}")
    public ResponseEntity<List<CollectionPointsDTO>> findByCity(@PathVariable String city) {
        try {
            return ResponseEntity.ok(collectionPointService.findByCity(city));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/collection-points/capacity")
    public ResponseEntity<List<CollectionPointsDTO>> findByCapacityRange(
            @RequestParam BigDecimal min,
            @RequestParam BigDecimal max) {
        try {
            return ResponseEntity.ok(collectionPointService.findByCapacityRange(min, max));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/collection-points/search")
    public ResponseEntity<List<CollectionPointsDTO>> findByNameContaining(@RequestParam String name) {
        try {
            return ResponseEntity.ok(collectionPointService.findByNameContaining(name));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}