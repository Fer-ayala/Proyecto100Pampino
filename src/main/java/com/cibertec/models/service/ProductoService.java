package com.cibertec.models.service;

import com.cibertec.models.entity.Producto;

import java.util.List;

public interface ProductoService {
    public List<Producto> listarTodos();

    public void guardar(Producto producto);

    public Producto buscarPorId(Integer Id);

    public void eliminar(Integer Id);
}
