package com.cibertec.models.repository;

import com.cibertec.models.entity.InventarioDetalle;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventarioDetalleRepository  extends CrudRepository<InventarioDetalle, Integer> {
}
