package com.cibertec.models.repository;

import com.cibertec.models.entity.Producto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository  extends CrudRepository<Producto, Integer> {
}
