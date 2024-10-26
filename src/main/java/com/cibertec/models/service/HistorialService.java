package com.cibertec.models.service;

import java.util.List;

import com.cibertec.models.entity.Historial;

public interface HistorialService {
	
	public List<Historial> listarTodos();
	
	public void guardar(Historial historial);

	public Historial buscarPorId(Integer Id);
	
	public void eliminar(Integer Id);
}
