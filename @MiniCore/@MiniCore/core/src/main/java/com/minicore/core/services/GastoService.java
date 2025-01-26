package com.minicore.core.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.minicore.core.models.Gasto;
import com.minicore.core.repositories.GastoRepository;

@Service
public class GastoService {
    @Autowired
    private GastoRepository gastoRepository;

    public List<Map<String, Object>> calcularGastosPorFechas(LocalDate startDate, LocalDate endDate){
        return gastoRepository.findGastosByFecha(startDate, endDate);
    }

    public List<Gasto> obtenerTodosGastos() {
        return gastoRepository.findAll();
    }

    public boolean agregarGasto(Gasto newGasto){
        gastoRepository.save(newGasto);
        return true;
    }

    public Gasto actualizarGasto(Long id, Gasto gasto) {
        return gastoRepository.findById(id)
                .map(g -> {
                    g.setDescripcion(gasto.getDescripcion());
                    g.setMonto(gasto.getMonto());
                    g.setFecha(gasto.getFecha());
                    g.setEmpleado(gasto.getEmpleado());
                    g.setDepartamento(gasto.getDepartamento());
                    return gastoRepository.save(g);
                })
                .orElseThrow(() -> new RuntimeException("Gasto no encontrado"));
    }

    public void eliminarGasto(Long id) {
        gastoRepository.deleteById(id);
    }
}
