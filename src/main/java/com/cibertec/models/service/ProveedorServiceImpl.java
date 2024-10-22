package com.cibertec.models.service;

import com.cibertec.models.entity.Proveedor;
import com.cibertec.models.repository.ProveedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProveedorServiceImpl implements ProveedorService {
    @Autowired
    private ProveedorRepository repository;

    @Override
    public List<Proveedor> listarTodos() {
        return (List<Proveedor>) repository.findAll();
    }

    @Override
    public void guardar(Proveedor producto) {
        repository.save(producto);
    }

    @Override
    public Proveedor buscarPorId(Integer Id) {
        return repository.findById(Id).orElse(null);
    }

    @Override
    public void eliminar(Integer Id) {
        repository.deleteById(Id);
    }
}
