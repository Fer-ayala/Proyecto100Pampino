package com.cibertec.models.repository;

import com.cibertec.models.entity.Inventario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventarioRepository extends CrudRepository<Inventario, Integer> {
}
