package br.com.rota_verde.rota_verde.collections.repository;

import br.com.rota_verde.rota_verde.collections.model.CollectionsModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CollectionsRepository extends JpaRepository<CollectionsModel, Long> {
}
