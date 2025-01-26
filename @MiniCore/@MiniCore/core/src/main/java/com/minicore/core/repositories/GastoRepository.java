package com.minicore.core.repositories;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.minicore.core.models.Gasto;

@Repository
public interface GastoRepository extends JpaRepository<Gasto, Long>{
    @Query("SELECT g.departamento.nombre AS departamento, SUM(g.monto) AS total " +
       "FROM Gasto g " +
       "WHERE g.fecha BETWEEN :startDate AND :endDate " +
       "GROUP BY g.departamento.nombre")
    List<Map<String, Object>> findGastosByFecha(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
    
}
