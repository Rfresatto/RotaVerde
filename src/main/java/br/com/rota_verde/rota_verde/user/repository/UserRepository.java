package br.com.rota_verde.rota_verde.user.repository;

import br.com.rota_verde.rota_verde.user.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<UserModel, Long> {

    UserDetails findByEmail(String email);

}
