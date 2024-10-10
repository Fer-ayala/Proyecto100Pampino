package com.cibertec.models.service;

import com.cibertec.models.entity.Producto;
import com.cibertec.models.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductoServiceImpl implements ProductoService{

    @Autowired
    private ProductoRepository repository;

    @Override
    public List<Producto> listarTodos() {
        return (List<Producto>) repository.findAll();
    }

    @Override
    public void guardar(Producto producto) {
        repository.save(producto);
    }

    @Override
    public Producto buscarPorId(Integer Id) {
        return repository.findById(Id).orElse(null);
    }

    @Override
    public void eliminar(Integer Id) {
        repository.deleteById(Id);
    }
}
