package com.cibertec.models.repository;

import com.cibertec.models.entity.Historial;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistorialRepository extends CrudRepository<Historial, Integer>{

}
