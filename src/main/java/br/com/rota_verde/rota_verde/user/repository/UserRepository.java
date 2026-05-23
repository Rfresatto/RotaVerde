package br.com.rota_verde.rota_verde.user.repository;

import br.com.rota_verde.rota_verde.user.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserModel, Long> {
}
