package com.cibertec.models.service;

import com.cibertec.models.entity.InventarioDetalle;
import com.cibertec.models.repository.InventarioDetalleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class InventarioDetalleServiceImpl implements InventarioDetalleService{
    @Autowired
    private InventarioDetalleRepository repository;

    @Override
    public List<InventarioDetalle> listarTodos() {
        return (List<InventarioDetalle>) repository.findAll();
    }

    @Override
    public void guardar(InventarioDetalle producto) {
        repository.save(producto);
    }

    @Override
    public InventarioDetalle buscarPorId(Integer Id) {
        return repository.findById(Id).orElse(null);
    }

    @Override
    public void eliminar(Integer Id) {
        repository.deleteById(Id);
    }
}
