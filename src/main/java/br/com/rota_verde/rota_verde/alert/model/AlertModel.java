package br.com.rota_verde.rota_verde.alert.model;

import br.com.rota_verde.rota_verde.containers.model.ContainerModel;
import br.com.rota_verde.rota_verde.notifications.model.NotificationModel;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "RV_T_ALERT")
public class AlertModel {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "RV_SEQ_ALERT")
    @SequenceGenerator(
            name = "RV_SEQ_ALERT",
            sequenceName = "RV_SEQ_ALERT",
            allocationSize = 1
    )
    @Column(name = "ID_ALERT")
    private Long idAlert;

    @ManyToOne
    @JoinColumn(name = "ID_CONTAINER", nullable = false)
    @JsonBackReference("container-alert")
    private ContainerModel container;

    @Column(name = "ALERT_TYPE", nullable = false, length = 50)
    private String alertType;

    @Column(name = "MESSAGE", nullable = false, length = 500)
    private String message;

    @Column(name = "CREATED_AT", nullable = false)
    private LocalDate createdAt;

    @Column(name = "RESOLVED", nullable = false)
    private Character resolved = 'N';

    @Column(name = "RESOLVED_AT")
    private LocalDate resolvedAt;

    @OneToMany(mappedBy = "alert", cascade = CascadeType.ALL)
    @JsonManagedReference("alert-notification")
    private List<NotificationModel> notifications;
}