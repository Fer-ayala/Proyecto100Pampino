package com.cibertec.models.entity;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name="inventario_detalle")
@NamedQuery(name="InventarioDetalle.findAll", query="SELECT e FROM InventarioDetalle e")
public class InventarioDetalle implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="producto_id")
    private int productoId;

    @Column(name="inventario_id")
    private int inventarioId;

    @Column(name="cantidad_ingreso")
    private int cantidadIngreso;

    @Column(name="cantidad_salida")
    private int cantidadSalida;

    public InventarioDetalle() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProductoId() {
        return productoId;
    }

    public void setProductoId(int productoId) {
        this.productoId = productoId;
    }

    public int getInventarioId() {
        return inventarioId;
    }

    public void setInventarioId(int inventarioId) {
        this.inventarioId = inventarioId;
    }

    public int getCantidadIngreso() {
        return cantidadIngreso;
    }

    public void setCantidadIngreso(int cantidadIngreso) {
        this.cantidadIngreso = cantidadIngreso;
    }

    public int getCantidadSalida() {
        return cantidadSalida;
    }

    public void setCantidadSalida(int cantidadSalida) {
        this.cantidadSalida = cantidadSalida;
    }
}
