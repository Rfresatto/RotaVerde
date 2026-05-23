package br.com.rota_verde.rota_verde.collection_points.repository;

import br.com.rota_verde.rota_verde.collection_points.model.CollectionPointsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface CollectionPointsRepository extends JpaRepository<CollectionPointsModel, Long> {

    @Query("SELECT cp FROM CollectionPointsModel cp WHERE cp.capacityKg > :capacity")
    List<CollectionPointsModel> findLargePoints(@Param("capacity") BigDecimal capacity);

    @Query("SELECT cp FROM CollectionPointsModel cp WHERE cp.active = 'Y'")
    List<CollectionPointsModel> findActivePoints();

    @Query("SELECT cp FROM CollectionPointsModel cp WHERE LOWER(cp.city) LIKE LOWER(CONCAT('%',:city,'%'))")
    List<CollectionPointsModel> findByCity(@Param("city") String city);

    @Query("SELECT cp FROM CollectionPointsModel cp WHERE cp.capacityKg BETWEEN :min AND :max")
    List<CollectionPointsModel> findByCapacityRange(@Param("min") BigDecimal min, @Param("max") BigDecimal max);

    @Query("SELECT cp FROM CollectionPointsModel cp WHERE LOWER(cp.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<CollectionPointsModel> findByNameContaining(@Param("name") String name);
}
