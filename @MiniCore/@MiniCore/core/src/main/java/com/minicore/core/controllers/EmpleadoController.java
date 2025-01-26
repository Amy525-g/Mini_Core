package com.minicore.core.controllers;

import com.minicore.core.models.Empleado;
import com.minicore.core.services.EmpleadoService;
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
@RequestMapping("/api/empleados")
public class EmpleadoController {

    @Autowired
    private EmpleadoService empleadoService;

    @GetMapping
    public ResponseEntity<List<Empleado>> obtenerEmpleados() {
        List<Empleado> empleados = empleadoService.obtenerTodosEmpleados();
        return ResponseEntity.ok(empleados);
    }

    @PostMapping("/add")
    public ResponseEntity<Boolean> agregarEmpleado(@RequestBody Empleado newEmpleado) {
        boolean empleado = empleadoService.agregarEmpleado(newEmpleado);
        return ResponseEntity.ok(empleado);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Empleado> actualizarEmpleado(
            @PathVariable Long id, @RequestBody Empleado empleado) {
        Empleado actualizado = empleadoService.actualizarEmpleado(id, empleado);
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarEmpleado(@PathVariable Long id) {
        empleadoService.eliminarEmpleado(id);
        return ResponseEntity.noContent().build();
    }
}
