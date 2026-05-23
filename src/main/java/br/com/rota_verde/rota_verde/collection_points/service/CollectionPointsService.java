package br.com.rota_verde.rota_verde.collection_points.service;

import br.com.rota_verde.rota_verde.collection_points.dto.CollectionPointsDTO;
import br.com.rota_verde.rota_verde.collection_points.dto.CreateCollectionPointsDTO;
import br.com.rota_verde.rota_verde.collection_points.model.CollectionPointsModel;
import br.com.rota_verde.rota_verde.collection_points.repository.CollectionPointsRepository;
import lombok.Getter;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Service
public class CollectionPointsService {

    @Autowired
    private CollectionPointsRepository collectionPointRepository;

    public CollectionPointsDTO saveCollectionPoint(CreateCollectionPointsDTO dto) {
        CollectionPointsModel model = new CollectionPointsModel();
        BeanUtils.copyProperties(dto, model);
        return new CollectionPointsDTO(collectionPointRepository.save(model));
    }

    public CollectionPointsDTO findCollectionPoint(Long id) {
        return new CollectionPointsDTO(collectionPointRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ponto de coleta não encontrado.")));
    }

    public List<CollectionPointsDTO> findManyCollectionPoints() {
        return collectionPointRepository.findAll().stream()
                .map(CollectionPointsDTO::new).toList();
    }

    public void deleteCollectionPoint(Long id) {
        CollectionPointsModel model = collectionPointRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ponto de coleta não encontrado."));
        collectionPointRepository.delete(model);
    }

    public CollectionPointsDTO updateCollectionPoint(Long id, CreateCollectionPointsDTO dto) {
        CollectionPointsModel model = collectionPointRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ponto de coleta não encontrado."));
        BeanUtils.copyProperties(dto, model);
        return new CollectionPointsDTO(collectionPointRepository.save(model));
    }

    public List<CollectionPointsDTO> findLargePoints() {
        return collectionPointRepository.findLargePoints(new BigDecimal("300"))
                .stream().map(CollectionPointsDTO::new).toList();
    }

    public List<CollectionPointsDTO> findActivePoints() {
        return collectionPointRepository.findActivePoints()
                .stream().map(CollectionPointsDTO::new).toList();
    }

    public List<CollectionPointsDTO> findByCity(String city) {
        return collectionPointRepository.findByCity(city)
                .stream().map(CollectionPointsDTO::new).toList();
    }

    public List<CollectionPointsDTO> findByCapacityRange(BigDecimal min, BigDecimal max) {
        return collectionPointRepository.findByCapacityRange(min, max)
                .stream().map(CollectionPointsDTO::new).toList();
    }

    public List<CollectionPointsDTO> findByNameContaining(String name) {
        return collectionPointRepository.findByNameContaining(name)
                .stream().map(CollectionPointsDTO::new).toList();
    }
}