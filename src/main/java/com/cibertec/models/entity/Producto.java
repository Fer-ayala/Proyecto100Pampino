package com.cibertec.models.entity;

import jakarta.persistence.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name="producto")
@NamedQuery(name="Producto.findAll", query="SELECT e FROM Producto e")
public class Producto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="producto_id")
    private int id;

    @Column(name = "codigo", length = 25)
    @NotEmpty
    private String codigo;

    @Column(name = "nombre", length = 100)
    @NotEmpty
    private String nombre;

    @Column(name = "precio")
    @NotNull
    private BigDecimal precio;

    public Producto() {
    }

    public Producto(int id, String codigo, String nombre, BigDecimal precio) {
        this.id = id;
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
    }

    public Producto(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public  String getCodigo() {
        return codigo;
    }

    public void setCodigo( String codigo) {
        this.codigo = codigo;
    }

    public  String getNombre() {
        return nombre;
    }

    public void setNombre( String nombre) {
        this.nombre = nombre;
    }

    public  BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio( BigDecimal precio) {
        this.precio = precio;
    }
}
