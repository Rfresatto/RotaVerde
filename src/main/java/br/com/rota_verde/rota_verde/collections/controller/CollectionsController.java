package br.com.rota_verde.rota_verde.collections.controller;

import br.com.rota_verde.rota_verde.collections.dto.CollectionDTO;
import br.com.rota_verde.rota_verde.collections.dto.CreateCollectionDTO;
import br.com.rota_verde.rota_verde.collections.service.CollectionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CollectionsController {

    @Autowired
    private CollectionService collectionService;

    @PostMapping("/collection")
    public ResponseEntity<CollectionDTO> save(@RequestBody @Valid CreateCollectionDTO dto) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(collectionService.saveCollection(dto));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/collections")
    public ResponseEntity<List<CollectionDTO>> findMany() {
        try {
            return ResponseEntity.ok(collectionService.findManyCollections());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/collection/{id}")
    public ResponseEntity<CollectionDTO> find(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(collectionService.findCollection(id));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/collection/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            collectionService.deleteCollection(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/collection/{id}")
    public ResponseEntity<CollectionDTO> update(@PathVariable Long id, @RequestBody @Valid CreateCollectionDTO dto) {
        try {
            return ResponseEntity.ok(collectionService.updateCollection(id, dto));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/collections/container/{containerId}")
    public ResponseEntity<List<CollectionDTO>> findByContainer(@PathVariable Long containerId) {
        try {
            return ResponseEntity.ok(collectionService.findByContainer(containerId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/collections/date")
    public ResponseEntity<List<CollectionDTO>> findByDateRange(
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate) {
        try {
            return ResponseEntity.ok(collectionService.findByDateRange(startDate, endDate));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/collections/responsible/{responsible}")
    public ResponseEntity<List<CollectionDTO>> findByResponsible(@PathVariable String responsible) {
        try {
            return ResponseEntity.ok(collectionService.findByResponsible(responsible));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/collections/destination/{destination}")
    public ResponseEntity<List<CollectionDTO>> findByDestination(@PathVariable String destination) {
        try {
            return ResponseEntity.ok(collectionService.findByDestination(destination));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/collections/weight")
    public ResponseEntity<List<CollectionDTO>> findByMinWeight(@RequestParam BigDecimal weight) {
        try {
            return ResponseEntity.ok(collectionService.findByMinWeight(weight));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}