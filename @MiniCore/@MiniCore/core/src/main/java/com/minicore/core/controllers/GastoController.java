package com.minicore.core.controllers;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.minicore.core.models.Empleado;
import com.minicore.core.models.Gasto;
import com.minicore.core.services.GastoService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/gastos")
public class GastoController {
    @Autowired
    private GastoService gastoService;

    @GetMapping("/filtrar")
    public ResponseEntity<List<Map<String, Object>>> filtrarGastos(@RequestParam String fechaInicio, @RequestParam String fechaFin) {
        LocalDate startDate = LocalDate.parse(fechaInicio);
        LocalDate endDate = LocalDate.parse(fechaFin);

        List<Map<String, Object>> result = gastoService.calcularGastosPorFechas(startDate, endDate);
        return ResponseEntity.ok(result);
    }
    
    @GetMapping("/all")
    public ResponseEntity<List<Gasto>> obtenerGastos() {
        List<Gasto> gastos = gastoService.obtenerTodosGastos();
        return ResponseEntity.ok(gastos);
    }

    @PostMapping("/add")
    public ResponseEntity<Boolean> agregarGasto(@RequestBody Gasto newGasto) {
        boolean gasto = gastoService.agregarGasto(newGasto);
        return ResponseEntity.ok(gasto);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Gasto> actualizarGasto(@PathVariable Long id, @RequestBody Gasto gasto) {
        Gasto actualizado = gastoService.actualizarGasto(id, gasto);
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarGasto(@PathVariable Long id) {
        gastoService.eliminarGasto(id);
        return ResponseEntity.noContent().build();
    }
    
}
