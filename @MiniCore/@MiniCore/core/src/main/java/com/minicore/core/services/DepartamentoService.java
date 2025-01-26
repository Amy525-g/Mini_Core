package com.minicore.core.services;

import com.minicore.core.models.Departamento;
import com.minicore.core.repositories.DepartamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartamentoService {

    @Autowired
    private DepartamentoRepository departamentoRepository;

    public List<Departamento> obtenerTodosDepartamentos() {
        return departamentoRepository.findAll();
    }

    public boolean agregarDepartamento(Departamento newDepartamento){
        Departamento depto = new Departamento();
        depto.setId(newDepartamento.getId());
        depto.setNombre(newDepartamento.getNombre());
        departamentoRepository.save(depto);
        return true;
    }

    public Departamento actualizarDepartamento(Long id, Departamento departamento) {
        return departamentoRepository.findById(id)
                .map(dep -> {
                    dep.setNombre(departamento.getNombre());
                    return departamentoRepository.save(dep);
                })
                .orElseThrow(() -> new RuntimeException("Departamento no encontrado"));
    }

    public void eliminarDepartamento(Long id) {
        departamentoRepository.deleteById(id);
    }
}
