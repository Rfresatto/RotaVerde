package br.com.rota_verde.rota_verde.collections.service;

import br.com.rota_verde.rota_verde.collections.dto.CollectionDTO;
import br.com.rota_verde.rota_verde.collections.dto.CreateCollectionDTO;
import br.com.rota_verde.rota_verde.collections.model.CollectionsModel;
import br.com.rota_verde.rota_verde.collections.repository.CollectionsRepository;
import br.com.rota_verde.rota_verde.containers.model.ContainerModel;
import br.com.rota_verde.rota_verde.containers.repository.ContainerRepository;
import br.com.rota_verde.rota_verde.exception.CollectionNotFoundException;
import br.com.rota_verde.rota_verde.exception.ContainerNotFoundException;
import lombok.Getter;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
@Service
public class CollectionService {

    @Autowired
    private CollectionsRepository collectionRepository;

    @Autowired
    private ContainerRepository containerRepository;

    public CollectionDTO saveCollection(CreateCollectionDTO dto) {
        ContainerModel container = containerRepository.findById(dto.containerId())
                .orElseThrow(() -> new ContainerNotFoundException("Container não encontrado."));

        CollectionsModel model = new CollectionsModel();
        BeanUtils.copyProperties(dto, model);
        model.setContainer(container);
        model.setCollectionDate(LocalDate.now());
        return new CollectionDTO(collectionRepository.save(model));
    }

    public CollectionDTO findCollection(Long id) {
        return new CollectionDTO(collectionRepository.findById(id)
                .orElseThrow(() -> new CollectionNotFoundException("Coleta não encontrada.")));
    }

    public Page<CollectionDTO> findManyCollections(Pageable pageable
    ) {
        return collectionRepository.findAll(pageable).map(CollectionDTO::new);
    }

    public void deleteCollection(Long id) {
        CollectionsModel model = collectionRepository.findById(id)
                .orElseThrow(() -> new CollectionNotFoundException("Coleta não encontrada."));
        collectionRepository.delete(model);
    }

    public CollectionDTO updateCollection(Long id, CreateCollectionDTO dto) {
        CollectionsModel model = collectionRepository.findById(id)
                .orElseThrow(() -> new CollectionNotFoundException("Coleta não encontrada."));
        ContainerModel container = containerRepository.findById(dto.containerId())
                .orElseThrow(() -> new ContainerNotFoundException("Container não encontrado."));
        BeanUtils.copyProperties(dto, model);
        model.setContainer(container);
        return new CollectionDTO(collectionRepository.save(model));
    }

    public List<CollectionDTO> findByContainer(Long containerId) {
        return collectionRepository.findByContainer(containerId)
                .stream().map(CollectionDTO::new).toList();
    }

    public List<CollectionDTO> findByDateRange(LocalDate startDate, LocalDate endDate) {
        return collectionRepository.findByDateRange(startDate, endDate)
                .stream().map(CollectionDTO::new).toList();
    }

    public List<CollectionDTO> findByResponsible(String responsible) {
        return collectionRepository.findByResponsible(responsible)
                .stream().map(CollectionDTO::new).toList();
    }

    public List<CollectionDTO> findByDestination(String destination) {
        return collectionRepository.findByDestination(destination)
                .stream().map(CollectionDTO::new).toList();
    }

    public List<CollectionDTO> findByMinWeight(BigDecimal weight) {
        return collectionRepository.findByMinWeight(weight)
                .stream().map(CollectionDTO::new).toList();
    }
}