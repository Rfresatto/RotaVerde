package br.com.rota_verde.rota_verde.collections.controller;

import br.com.rota_verde.rota_verde.collections.dto.CollectionDTO;
import br.com.rota_verde.rota_verde.collections.dto.CreateCollectionDTO;
import br.com.rota_verde.rota_verde.collections.service.CollectionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
            return ResponseEntity.status(HttpStatus.CREATED).body(collectionService.saveCollection(dto));
    }

    @GetMapping("/collections")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Page<CollectionDTO>> findMany(
            @PageableDefault(size = 5, page = 0)
            Pageable pageable
    ) {
            return ResponseEntity.ok(collectionService.findManyCollections(pageable));
    }

    @GetMapping("/collection/{id}")
    public ResponseEntity<CollectionDTO> find(@PathVariable Long id) {
            return ResponseEntity.ok(collectionService.findCollection(id));
    }

    @DeleteMapping("/collection/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
            collectionService.deleteCollection(id);
            return ResponseEntity.noContent().build();
    }

    @PutMapping("/collection/{id}")
    public ResponseEntity<CollectionDTO> update(@PathVariable Long id, @RequestBody @Valid CreateCollectionDTO dto) {
            return ResponseEntity.ok(collectionService.updateCollection(id, dto));
    }

    @GetMapping("/collections/container/{containerId}")
    public ResponseEntity<List<CollectionDTO>> findByContainer(@PathVariable Long containerId) {
            return ResponseEntity.ok(collectionService.findByContainer(containerId));
    }

    @GetMapping("/collections/date")
    public ResponseEntity<List<CollectionDTO>> findByDateRange(
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate) {
            return ResponseEntity.ok(collectionService.findByDateRange(startDate, endDate));
    }

    @GetMapping("/collections/responsible/{responsible}")
    public ResponseEntity<List<CollectionDTO>> findByResponsible(@PathVariable String responsible) {
            return ResponseEntity.ok(collectionService.findByResponsible(responsible));
    }

    @GetMapping("/collections/destination/{destination}")
    public ResponseEntity<List<CollectionDTO>> findByDestination(@PathVariable String destination) {
            return ResponseEntity.ok(collectionService.findByDestination(destination));
    }

    @GetMapping("/collections/weight")
    public ResponseEntity<List<CollectionDTO>> findByMinWeight(@RequestParam BigDecimal weight) {
            return ResponseEntity.ok(collectionService.findByMinWeight(weight));
    }
}