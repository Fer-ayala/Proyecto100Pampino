package com.cibertec.models.service;

import java.util.List;

import com.cibertec.models.entity.Historial;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cibertec.models.repository.HistorialRepository;

@Service
public class HistorialServiceImpl implements HistorialService {
	
	@Autowired
	private HistorialRepository historialRepository;

	@Override
	public List<Historial> listarTodos() {
		// TODO Auto-generated method stub
		return (List<Historial>) historialRepository.findAll();
	}

	@Override
	public void guardar(Historial historial) {
		// TODO Auto-generated method 
		historialRepository.save(historial);
	}

	@Override
	public Historial buscarPorId(Integer Id) {
		// TODO Auto-generated method stub
		return historialRepository.findById(Id).orElse(null);
	}

	@Override
	public void eliminar(Integer Id) {
		// TODO Auto-generated method stub
		historialRepository.deleteById(Id);
	}

}
