package br.com.rota_verde.rota_verde.collections.model;

import br.com.rota_verde.rota_verde.containers.model.ContainerModel;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "RV_T_COLLECTION")
public class CollectionsModel {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "RV_SEQ_COLLECTION")
    @SequenceGenerator(
            name = "RV_SEQ_COLLECTION",
            sequenceName = "RV_SEQ_COLLECTION",
            allocationSize = 1
    )
    @Column(name = "ID_COLLECTION")
    private Long collectionId;

    @ManyToOne
    @JoinColumn(name = "ID_CONTAINER", nullable = false)
    @JsonBackReference("container-collection")
    private ContainerModel container;

    @Column(name = "COLLECTION_DATE", nullable = false)
    private LocalDate collectionDate;

    @Column(name = "COLLECTED_WEIGHT_KG", nullable = false, precision = 10, scale = 2)
    private BigDecimal collectedWeightKg;

    @Column(name = "RESPONSIBLE", nullable = false, length = 100)
    private String responsible;

    @Column(name = "DESTINATION", nullable = false, length = 100)
    private String destination;

    @Column(name = "NOTES", length = 300)
    private String notes;
}
