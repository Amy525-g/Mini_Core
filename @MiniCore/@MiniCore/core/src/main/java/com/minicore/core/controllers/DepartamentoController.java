package com.minicore.core.controllers;

import com.minicore.core.models.Departamento;
import com.minicore.core.services.DepartamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/departamentos")
public class DepartamentoController {

    @Autowired
    private DepartamentoService departamentoService;

    @GetMapping
    public ResponseEntity<List<Departamento>> obtenerDepartamentos() {
        List<Departamento> departamentos = departamentoService.obtenerTodosDepartamentos();
        return ResponseEntity.ok(departamentos);
    }
    @PostMapping("/add")
    public ResponseEntity<Boolean> agregarDepartamentos(@RequestBody Departamento newDepartamento) {
        boolean departamento = departamentoService.agregarDepartamento(newDepartamento);
        return ResponseEntity.ok(departamento);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Departamento> actualizarDepartamento(
            @PathVariable Long id, @RequestBody Departamento departamento) {
        Departamento actualizado = departamentoService.actualizarDepartamento(id, departamento);
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarDepartamento(@PathVariable Long id) {
        departamentoService.eliminarDepartamento(id);
        return ResponseEntity.noContent().build();
    }
}
