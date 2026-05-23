package br.com.rota_verde.rota_verde.collections.repository;

import br.com.rota_verde.rota_verde.collections.model.CollectionsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface CollectionsRepository extends JpaRepository<CollectionsModel, Long> {

    @Query("SELECT c FROM CollectionsModel c WHERE c.container.containerId = :containerId")
    List<CollectionsModel> findByContainer(@Param("containerId") Long containerId);

    @Query("SELECT c FROM CollectionsModel c WHERE c.collectionDate BETWEEN :startDate AND :endDate")
    List<CollectionsModel> findByDateRange(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    @Query("SELECT c FROM CollectionsModel c WHERE LOWER(c.responsible) LIKE LOWER(CONCAT('%',:responsible,'%'))")
    List<CollectionsModel> findByResponsible(@Param("responsible") String responsible);

    @Query("SELECT c FROM CollectionsModel c WHERE LOWER(c.destination) LIKE LOWER(CONCAT('%',:destination,'%'))")
    List<CollectionsModel> findByDestination(@Param("destination") String destination);

    @Query("SELECT c FROM CollectionsModel c WHERE c.collectedWeightKg >= :weight")
    List<CollectionsModel> findByMinWeight(@Param("weight") BigDecimal weight);
}
