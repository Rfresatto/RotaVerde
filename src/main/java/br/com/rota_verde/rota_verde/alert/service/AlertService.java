package br.com.rota_verde.rota_verde.alert.service;

import br.com.rota_verde.rota_verde.alert.dto.AlertDTO;
import br.com.rota_verde.rota_verde.alert.dto.CreateAlertDTO;
import br.com.rota_verde.rota_verde.alert.model.AlertModel;
import br.com.rota_verde.rota_verde.alert.repository.AlertRepository;
import br.com.rota_verde.rota_verde.containers.model.ContainerModel;
import br.com.rota_verde.rota_verde.containers.repository.ContainerRepository;
import br.com.rota_verde.rota_verde.exception.AlertNotFoundException;
import br.com.rota_verde.rota_verde.exception.ContainerNotFoundException;
import lombok.Getter;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Getter
@Service
public class AlertService {

    @Autowired
    private AlertRepository alertRepository;

    @Autowired
    private ContainerRepository containerRepository;

    public AlertDTO saveAlert(CreateAlertDTO dto) {
        ContainerModel container = containerRepository.findById(dto.containerId())
                .orElseThrow(() -> new ContainerNotFoundException("Container não encontrado."));

        AlertModel model = new AlertModel();
        BeanUtils.copyProperties(dto, model);
        model.setContainer(container);
        model.setCreatedAt(LocalDate.now());
        return new AlertDTO(alertRepository.save(model));
    }

    public AlertDTO findAlert(Long id) {
        return new AlertDTO(alertRepository.findById(id)
                .orElseThrow(() -> new AlertNotFoundException("Alerta não encontrado.")));
    }

    public Page<AlertDTO> findManyAlerts(Pageable pageable) {
        return alertRepository.findAll(pageable).map(AlertDTO::new);
    }

    public void deleteAlert(Long id) {
        AlertModel model = alertRepository.findById(id)
                .orElseThrow(() -> new AlertNotFoundException("Alerta não encontrado."));
        alertRepository.delete(model);
    }

    public AlertDTO updateAlert(Long id, CreateAlertDTO dto) {
        AlertModel model = alertRepository.findById(id)
                .orElseThrow(() -> new AlertNotFoundException("Alerta não encontrado."));
        ContainerModel container = containerRepository.findById(dto.containerId())
                .orElseThrow(() -> new ContainerNotFoundException("Container não encontrado."));
        BeanUtils.copyProperties(dto, model);
        model.setContainer(container);
        return new AlertDTO(alertRepository.save(model));
    }

    public List<AlertDTO> findUnresolvedAlerts() {
        return alertRepository.findUnresolvedAlerts()
                .stream().map(AlertDTO::new).toList();
    }

    public List<AlertDTO> findByAlertType(String alertType) {
        return alertRepository.findByAlertType(alertType)
                .stream().map(AlertDTO::new).toList();
    }

    public List<AlertDTO> findByContainer(Long containerId) {
        return alertRepository.findByContainer(containerId)
                .stream().map(AlertDTO::new).toList();
    }

    public List<AlertDTO> findByDateRange(LocalDate startDate, LocalDate endDate) {
        return alertRepository.findByDateRange(startDate, endDate)
                .stream().map(AlertDTO::new).toList();
    }

    public List<AlertDTO> findResolvedAlerts() {
        return alertRepository.findResolvedAlerts()
                .stream().map(AlertDTO::new).toList();
    }
}