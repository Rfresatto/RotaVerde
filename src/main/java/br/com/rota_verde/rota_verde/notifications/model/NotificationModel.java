package br.com.rota_verde.rota_verde.notifications.model;

import br.com.rota_verde.rota_verde.alert.model.AlertModel;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "RV_T_USER_NOTIFICATION")
public class NotificationModel {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "RV_SEQ_USER_NOTIFICATION")
    @SequenceGenerator(
            name = "RV_SEQ_USER_NOTIFICATION",
            sequenceName = "RV_SEQ_USER_NOTIFICATION",
            allocationSize = 1
    )
    @Column(name = "ID_NOTIFICATION")
    private Long notificationId;

    @ManyToOne
    @JoinColumn(name = "ID_ALERT", nullable = false)
    @JsonBackReference("alert-notification")
    private AlertModel alert;

    @Column(name = "RECIPIENT", nullable = false, length = 150)
    private String recipient;

    @Column(name = "CHANNEL", nullable = false, length = 20)
    private String channel;

    @Column(name = "MESSAGE", nullable = false, length = 500)
    private String message;

    @Column(name = "SENT_AT", nullable = false)
    private LocalDate sentAt;

    @Column(name = "SENT", nullable = false)
    private Character sent = 'N';
}
