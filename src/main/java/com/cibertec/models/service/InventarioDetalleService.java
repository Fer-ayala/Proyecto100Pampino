package com.cibertec.models.service;

import com.cibertec.models.entity.InventarioDetalle;

import java.util.List;

public interface InventarioDetalleService {
    public List<InventarioDetalle> listarTodos();

    public void guardar(InventarioDetalle producto);

    public InventarioDetalle buscarPorId(Integer Id);

    public void eliminar(Integer Id);
}
