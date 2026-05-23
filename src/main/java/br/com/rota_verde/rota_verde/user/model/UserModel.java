package br.com.rota_verde.rota_verde.user.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "RV_T_USERS")
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "RV_SEQ_USERS")
    @SequenceGenerator(
            name = "RV_SEQ_USERS",
            sequenceName = "RV_SEQ_USERS",
            allocationSize = 1
    )
    @Column(name = "USER_OID")
    private Long userOid;
    @Column(name = "NAME")
    private String name;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "PASSWORD")
    private String password;
}
