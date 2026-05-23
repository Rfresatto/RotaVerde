package br.com.rota_verde.rota_verde.collection_points.model;

import br.com.rota_verde.rota_verde.containers.model.ContainerModel;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
@Table(name = "RV_T_COLLECTION_POINT")
public class CollectionPointsModel {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "RV_SEQ_COLLECTION_POINT")
    @SequenceGenerator(
            name = "RV_SEQ_COLLECTION_POINT",
            sequenceName = "RV_SEQ_COLLECTION_POINT",
            allocationSize = 1
    )
    @Column(name = "ID_POINT")
    private Long pointId;

    @Column(name = "NAME", nullable = false, length = 100)
    private String name;

    @Column(name = "ADDRESS", nullable = false, length = 200)
    private String address;

    @Column(name = "CITY", nullable = false, length = 100)
    private String city;

    @Column(name = "CAPACITY_KG", nullable = false, precision = 10, scale = 2)
    private BigDecimal capacityKg;

    @Column(name = "ACTIVE", nullable = false)
    private Character active = 'Y';

    @OneToMany(mappedBy = "collectionPoint", cascade = CascadeType.ALL)
    @JsonManagedReference("collectionPoint-container")
    private List<ContainerModel> containers;
}