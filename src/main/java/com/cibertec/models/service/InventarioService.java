package com.cibertec.models.service;

import com.cibertec.models.entity.Inventario;

import java.util.List;

public interface InventarioService {
    public List<Inventario> listarTodos();

    public void guardar(Inventario producto);

    public Inventario buscarPorId(Integer Id);

    public void eliminar(Integer Id);
}
