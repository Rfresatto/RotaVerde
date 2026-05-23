package br.com.rota_verde.rota_verde.alert.repository;

import br.com.rota_verde.rota_verde.alert.model.AlertModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface AlertRepository extends JpaRepository<AlertModel, Long> {

    @Query("SELECT a FROM AlertModel a WHERE a.resolved = 'N'")
    List<AlertModel> findUnresolvedAlerts();

    @Query("SELECT a FROM AlertModel a WHERE a.alertType = :alertType")
    List<AlertModel> findByAlertType(@Param("alertType") String alertType);

    @Query("SELECT a FROM AlertModel a WHERE a.container.containerId = :containerId")
    List<AlertModel> findByContainer(@Param("containerId") Long containerId);

    @Query("SELECT a FROM AlertModel a WHERE a.createdAt BETWEEN :startDate AND :endDate")
    List<AlertModel> findByDateRange(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    @Query("SELECT a FROM AlertModel a WHERE a.resolved = 'Y'")
    List<AlertModel> findResolvedAlerts();
}
