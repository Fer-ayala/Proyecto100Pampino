package com.cibertec.models.repository;

import com.cibertec.models.entity.Proveedor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProveedorRepository extends CrudRepository<Proveedor, Integer> {
}
