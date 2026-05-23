package br.com.rota_verde.rota_verde.containers.model;

import br.com.rota_verde.rota_verde.alert.model.AlertModel;
import br.com.rota_verde.rota_verde.collection_points.model.CollectionPointsModel;
import br.com.rota_verde.rota_verde.collections.model.CollectionsModel;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "RV_T_CONTAINER")
public class ContainerModel {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "RV_SEQ_CONTAINER")
    @SequenceGenerator(
            name = "RV_SEQ_CONTAINER",
            sequenceName = "RV_SEQ_CONTAINER",
            allocationSize = 1
    )
    @Column(name = "ID_CONTAINER")
    private Long containerId;

    @ManyToOne
    @JoinColumn(name = "ID_POINT", nullable = false)
    @JsonBackReference("collectionPoint-container")
    private CollectionPointsModel collectionPoint;

    @Column(name = "WASTE_TYPE", nullable = false, length = 50)
    private String wasteType;

    @Column(name = "CAPACITY_KG", nullable = false, precision = 10, scale = 2)
    private BigDecimal capacityKg;

    @Column(name = "CURRENT_VOLUME_KG", nullable = false)
    private BigDecimal currentVolumeKg = BigDecimal.ZERO;

    @Column(name = "USAGE_PERCENTAGE", insertable = false, updatable = false)
    private BigDecimal usagePercentage;

    @OneToMany(mappedBy = "container", cascade = CascadeType.ALL)
    @JsonBackReference("container-alert")
    private List<AlertModel> alerts;

    @OneToMany(mappedBy = "container", cascade = CascadeType.ALL)
    @JsonBackReference("container-collection")
    private List<CollectionsModel> collections;
}
