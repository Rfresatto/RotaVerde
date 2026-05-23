package br.com.rota_verde.rota_verde.containers.repository;

import br.com.rota_verde.rota_verde.containers.model.ContainerModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface ContainerRepository extends JpaRepository<ContainerModel, Long> {

    @Query("SELECT c FROM ContainerModel c WHERE c.wasteType = :wasteType")
    List<ContainerModel> findByWasteType(@Param("wasteType") String wasteType);

    @Query("SELECT c FROM ContainerModel c WHERE c.usagePercentage >= :percentage")
    List<ContainerModel> findByCriticalUsage(@Param("percentage") BigDecimal percentage);

    @Query("SELECT c FROM ContainerModel c WHERE c.collectionPoint.pointId = :pointId")
    List<ContainerModel> findByCollectionPoint(@Param("pointId") Long pointId);

    @Query("SELECT c FROM ContainerModel c WHERE c.currentVolumeKg = 0")
    List<ContainerModel> findEmptyContainers();

    @Query("SELECT c FROM ContainerModel c WHERE c.capacityKg BETWEEN :min AND :max")
    List<ContainerModel> findByCapacityRange(@Param("min") BigDecimal min, @Param("max") BigDecimal max);
}
