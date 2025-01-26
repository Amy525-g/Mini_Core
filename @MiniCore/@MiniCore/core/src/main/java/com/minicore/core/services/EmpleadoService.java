package com.minicore.core.services;

import com.minicore.core.models.Empleado;
import com.minicore.core.repositories.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpleadoService {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    public List<Empleado> obtenerTodosEmpleados() {
        return empleadoRepository.findAll();
    }

    public boolean agregarEmpleado(Empleado newEmpleado){
        empleadoRepository.save(newEmpleado);
        return true;
    }

    public Empleado actualizarEmpleado(Long id, Empleado empleado) {
        return empleadoRepository.findById(id)
                .map(emp -> {
                    emp.setNombre(empleado.getNombre());
                    emp.setDepartamento(empleado.getDepartamento());
                    return empleadoRepository.save(emp);
                })
                .orElseThrow(() -> new RuntimeException("Empleado no encontrado"));
    }

    public void eliminarEmpleado(Long id) {
        empleadoRepository.deleteById(id);
    }
}
