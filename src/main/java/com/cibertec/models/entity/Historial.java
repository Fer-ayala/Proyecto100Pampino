package com.cibertec.models.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

@Entity
@Table(name="Historial")
@NamedQuery(name="Historial.findAll", query="SELECT e FROM Historial e")
public class Historial implements Serializable {
	
	private static final long serialVersionUID = 1L;
		
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name="id")
		private int id;
		
		@Column(name = "codigo", length = 25)
		@NotEmpty
		private String codigo;
		
		@Column(name = "nombre", length = 100)
		@NotEmpty
		private String nombre;
		
		@Column(name = "descripcion", length = 300)
		@NotNull
		private String descripcion;
		
		@Column(name = "stock")
		@NotNull
		private int stock;
		
		@Column(name = "precio")
		@NotNull
		private BigDecimal precio;

		private Producto producto;

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}
		
		public Historial() {
			
		}
		
		public Historial(String codigo, String nombre, String consola, String genero, String descripcion, int stock, BigDecimal precio) {
			super();
			this.codigo = codigo;
			this.nombre = nombre;
			this.descripcion = descripcion;
			this.stock = stock;
			this.precio = precio;
		}

		public Historial(int id, String codigo, String nombre, String consola, String genero, String descripcion, int stock, BigDecimal precio) {
			super();
			this.id = id;
			this.codigo = codigo;
			this.nombre = nombre;
			this.descripcion = descripcion;
			this.stock = stock;
			this.precio = precio;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getCodigo() {
			return codigo;
		}

		public void setCodigo(String codigo) {
			this.codigo = codigo;
		}

		public String getNombre() {
			return nombre;
		}

		public void setNombre(String nombre) {
			this.nombre = nombre;
		}

		public String getDescripcion() {
			return descripcion;
		}

		public void setDescripcion(String descripcion) {
			this.descripcion = descripcion;
		}

		public int getStock() {
			return stock;
		}

		public void setStock(int stock) {
			this.stock = stock;
		}

		public BigDecimal getPrecio() {
			return precio;
		}

		public void setPrecio(BigDecimal precio) {
			this.precio = precio;
		}

		@Override
		public String toString() {
			return "Videojuego [id=" + id + ", codigo=" + codigo + ", nombre=" + nombre + ", consola="
					+ ", genero=" + ", descripcion=" + descripcion + ", stock=" + stock + ", precio=" + precio
					+ "]";
		}
			
}
