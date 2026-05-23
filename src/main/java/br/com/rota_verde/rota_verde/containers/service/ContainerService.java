package br.com.rota_verde.rota_verde.containers.service;

import br.com.rota_verde.rota_verde.collection_points.model.CollectionPointsModel;
import br.com.rota_verde.rota_verde.collection_points.repository.CollectionPointsRepository;
import br.com.rota_verde.rota_verde.containers.dto.ContainerDTO;
import br.com.rota_verde.rota_verde.containers.dto.CreateContainerDTO;
import br.com.rota_verde.rota_verde.containers.model.ContainerModel;
import br.com.rota_verde.rota_verde.containers.repository.ContainerRepository;
import lombok.Getter;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Service
public class ContainerService {

    @Autowired
    private ContainerRepository containerRepository;

    @Autowired
    private CollectionPointsRepository collectionPointRepository;

    public ContainerDTO saveContainer(CreateContainerDTO dto) {
        CollectionPointsModel collectionPoint = collectionPointRepository.findById(dto.collectionPointId())
                .orElseThrow(() -> new RuntimeException("Ponto de coleta não encontrado."));

        ContainerModel model = new ContainerModel();
        BeanUtils.copyProperties(dto, model);
        model.setCollectionPoint(collectionPoint);
        return new ContainerDTO(containerRepository.save(model));
    }

    public ContainerDTO findContainer(Long id) {
        return new ContainerDTO(containerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Container não encontrado.")));
    }

    public List<ContainerDTO> findManyContainers() {
        return containerRepository.findAll().stream()
                .map(ContainerDTO::new).toList();
    }

    public void deleteContainer(Long id) {
        ContainerModel model = containerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Container não encontrado."));
        containerRepository.delete(model);
    }

    public ContainerDTO updateContainer(Long id, CreateContainerDTO dto) {
        ContainerModel model = containerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Container não encontrado."));
        CollectionPointsModel collectionPoint = collectionPointRepository.findById(dto.collectionPointId())
                .orElseThrow(() -> new RuntimeException("Ponto de coleta não encontrado."));
        BeanUtils.copyProperties(dto, model);
        model.setCollectionPoint(collectionPoint);
        return new ContainerDTO(containerRepository.save(model));
    }

    public List<ContainerDTO> findByWasteType(String wasteType) {
        return containerRepository.findByWasteType(wasteType)
                .stream().map(ContainerDTO::new).toList();
    }

    public List<ContainerDTO> findByCriticalUsage() {
        return containerRepository.findByCriticalUsage(new BigDecimal("80"))
                .stream().map(ContainerDTO::new).toList();
    }

    public List<ContainerDTO> findByCollectionPoint(Long pointId) {
        return containerRepository.findByCollectionPoint(pointId)
                .stream().map(ContainerDTO::new).toList();
    }

    public List<ContainerDTO> findEmptyContainers() {
        return containerRepository.findEmptyContainers()
                .stream().map(ContainerDTO::new).toList();
    }

    public List<ContainerDTO> findByCapacityRange(BigDecimal min, BigDecimal max) {
        return containerRepository.findByCapacityRange(min, max)
                .stream().map(ContainerDTO::new).toList();
    }
}