package com.cibertec.models.service;


import com.cibertec.models.entity.Proveedor;

import java.util.List;

public interface ProveedorService {
    public List<Proveedor> listarTodos();

    public void guardar(Proveedor producto);

    public Proveedor buscarPorId(Integer Id);

    public void eliminar(Integer Id);
}
