package com.cibertec.models.service;

import com.cibertec.models.entity.Inventario;
import com.cibertec.models.repository.InventarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class InventarioServiceImpl implements InventarioService{
    @Autowired
    private InventarioRepository repository;

    @Override
    public List<Inventario> listarTodos() {
        return (List<Inventario>) repository.findAll();
    }

    @Override
    public void guardar(Inventario producto) {
        repository.save(producto);
    }

    @Override
    public Inventario buscarPorId(Integer Id) {
        return repository.findById(Id).orElse(null);
    }

    @Override
    public void eliminar(Integer Id) {
        repository.deleteById(Id);
    }
}
