-- Crear la base de datos
CREATE DATABASE IF NOT EXISTS inventario_pampino;
USE inventario_pampino;

-- Crear la tabla de productos
CREATE TABLE Producto (
    producto_id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    tipo VARCHAR(100),
    descripcion TEXT,
    precio DECIMAL(10, 2) NOT NULL,
    unidad_de_medida VARCHAR(50),
    fecha_de_caducidad DATE,
    stock_actual INT NOT NULL,
    stock_minimo INT NOT NULL
);

-- Crear la tabla de categorías
CREATE TABLE Categoria (
    categoria_id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    descripcion TEXT
);

-- Relación entre Producto y Categoria
ALTER TABLE Producto
ADD COLUMN categoria_id INT,
ADD FOREIGN KEY (categoria_id) REFERENCES Categoria(categoria_id);

-- Crear la tabla de proveedores
CREATE TABLE Proveedor (
    proveedor_id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    contacto VARCHAR(100),
    direccion TEXT,
    telefono VARCHAR(20),
    email VARCHAR(255),
    tiempo_de_entrega INT -- en días
);

-- Crear la tabla de compras
CREATE TABLE Compra (
    compra_id INT AUTO_INCREMENT PRIMARY KEY,
    proveedor_id INT,
    fecha_compra DATE NOT NULL,
    monto_total DECIMAL(10, 2),
    estado ENUM('pendiente', 'completada') DEFAULT 'pendiente',
    FOREIGN KEY (proveedor_id) REFERENCES Proveedor(proveedor_id)
);

-- Crear la tabla de detalle de compra
CREATE TABLE Detalle_Compra (
    detalle_compra_id INT AUTO_INCREMENT PRIMARY KEY,
    compra_id INT,
    producto_id INT,
    cantidad INT NOT NULL,
    precio_unitario DECIMAL(10, 2) NOT NULL,
    subtotal DECIMAL(10, 2) AS (cantidad * precio_unitario),
    FOREIGN KEY (compra_id) REFERENCES Compra(compra_id),
    FOREIGN KEY (producto_id) REFERENCES Producto(producto_id)
);

-- Crear la tabla de inventario
CREATE TABLE Inventario (
    inventario_id INT AUTO_INCREMENT PRIMARY KEY,
    producto_id INT,
    fecha DATE NOT NULL,
    cantidad_ingreso INT DEFAULT 0,
    cantidad_salida INT DEFAULT 0,
    cantidad_disponible INT NOT NULL,
    ubicacion VARCHAR(255),
    FOREIGN KEY (producto_id) REFERENCES Producto(producto_id)
);

-- Crear la tabla de ventas
CREATE TABLE Venta (
    venta_id INT AUTO_INCREMENT PRIMARY KEY,
    cliente_id INT,
    fecha_venta DATE NOT NULL,
    monto_total DECIMAL(10, 2),
    estado ENUM('pendiente', 'completada') DEFAULT 'pendiente'
);

-- Crear la tabla de detalle de venta
CREATE TABLE Detalle_Venta (
    detalle_venta_id INT AUTO_INCREMENT PRIMARY KEY,
    venta_id INT,
    producto_id INT,
    cantidad INT NOT NULL,
    precio_unitario DECIMAL(10, 2) NOT NULL,
    subtotal DECIMAL(10, 2) AS (cantidad * precio_unitario),
    FOREIGN KEY (venta_id) REFERENCES Venta(venta_id),
    FOREIGN KEY (producto_id) REFERENCES Producto(producto_id)
);

-- Crear la tabla de clientes
CREATE TABLE Cliente (
    cliente_id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    contacto VARCHAR(100),
    direccion TEXT,
    telefono VARCHAR(20),
    email VARCHAR(255),
    tipo_cliente ENUM('minorista', 'mayorista') DEFAULT 'minorista'
);

-- Relación entre Venta y Cliente
ALTER TABLE Venta
ADD FOREIGN KEY (cliente_id) REFERENCES Cliente(cliente_id);

-- Crear la tabla de almacén
CREATE TABLE Almacen (
    almacen_id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    ubicacion VARCHAR(255),
    capacidad INT,
    tipo_producto VARCHAR(100)
);

CREATE TABLE usuario (
	idusuario INT auto_increment NOT NULL,
	nomusuario varchar(100) NULL,
	email varchar(200) NULL,
	password varchar(300) NULL,
	nombres varchar(100) NULL,
	apellidos varchar(100) NULL,
	activo BOOL NULL,
	CONSTRAINT users_pk PRIMARY KEY (idusuario)
);


CREATE TABLE rol (
	idrol INT auto_increment NOT NULL,
	nomrol varchar(300) NULL,
	CONSTRAINT roles_pk PRIMARY KEY (idrol)
);

CREATE TABLE usuario_rol (
	idusuario INT NOT NULL,
	idrol INT NOT NULL,
	CONSTRAINT user_role_pk PRIMARY KEY (idusuario, idrol),
	CONSTRAINT user_role_FK FOREIGN KEY (idusuario) REFERENCES usuario(idusuario),
	CONSTRAINT user_role_FK_1 FOREIGN KEY (idrol) REFERENCES rol(idrol)
);

/* Inserción de datos */
INSERT INTO rol (idrol, nomrol) VALUES
(1, 'ADMIN'),
(2, 'COORDINADOR');

-- Registros para la tabla usuario
#contraseña de juan --> juancontrasena
#contraseña de maria --> mariacontrasena
INSERT INTO usuario (nomusuario, email, password, nombres, apellidos, activo)
VALUES
('juanperez', 'juan.perez@example.com', '$2a$12$QRMxXD7Pok3uTd992NqeeOpmyXbqteb6PvBb5I6CosxfM2mS5tO1K', 'Juan', 'Pérez', TRUE),
('maria.lopez', 'maria.lopez@example.com', '$2a$12$mlb0PL4f8DDYP5dVsaggcOMmTnHhogXmRYxLa9Oyl1f97ZbDwsSN6', 'María', 'López', TRUE);

INSERT INTO usuario_rol values 
(1,1),
(1,2),
(2,2);

/* Insert primeras tablas */

INSERT INTO Categoria (nombre, descripcion)
VALUES 
('Bebidas', 'Bebidas variadas, tanto naturales como procesadas'),
('Cereales', 'Cereales y harinas para distintas preparaciones'),
('Alimentos Enlatados', 'Alimentos que se conservan en lata'),
('Lácteos', 'Productos lácteos de diferentes tipos'),
('Panadería', 'Productos de panadería frescos y horneados');

INSERT INTO Producto (nombre, tipo, descripcion, precio, unidad_de_medida, fecha_de_caducidad, stock_actual, stock_minimo, categoria_id)
VALUES 
('Leche Entera', 'Lácteo', 'Leche de vaca pasteurizada', 1.50, 'Litro', '2025-01-15', 200, 50, 1),
('Pan Integral', 'Panadería', 'Pan hecho con harina integral', 0.80, 'Unidad', '2024-11-30', 300, 100, 2),
('Jugo de Naranja', 'Bebida', 'Jugo de naranja natural', 1.20, 'Litro', '2024-12-05', 150, 30, 1),
('Pasta de Tomate', 'Alimento enlatado', 'Concentrado de tomate', 0.90, 'Lata', '2025-03-20', 100, 20, 3),
('Harina de Maíz', 'Cereal', 'Harina de maíz precocida', 1.10, 'Kg', '2025-05-10', 250, 40, 2);

INSERT INTO Proveedor (nombre, contacto, direccion, telefono, email, tiempo_de_entrega)
VALUES 
('Distribuidora Lactosa', 'Ana López', 'Av. Principal 123, Ciudad', '123-4567', 'contacto@lactosa.com', 3),
('Panadería Don Pan', 'Carlos Ruiz', 'Calle 4 No. 56, Ciudad', '234-5678', 'ventas@donpan.com', 1),
('Frutas y Jugos S.A.', 'Mario Pérez', 'Carretera 9 Km 4, Ciudad', '345-6789', 'mario@frutasyjugos.com', 5),
('Conservas del Campo', 'Laura Torres', 'Calle Comercio 45, Ciudad', '456-7890', 'ltorres@campo.com', 2),
('Maíz y Más', 'Elena Hernández', 'Zona Industrial 7, Ciudad', '567-8901', 'elena@maizymas.com', 4);

INSERT INTO Compra (proveedor_id, fecha_compra, monto_total, estado)
VALUES 
(1, '2024-10-20', 300.00, 'completada'),
(2, '2024-10-22', 100.00, 'pendiente'),
(3, '2024-10-18', 150.00, 'completada'),
(4, '2024-10-19', 200.00, 'pendiente'),
(5, '2024-10-21', 250.00, 'completada');

INSERT INTO Detalle_Compra (compra_id, producto_id, cantidad, precio_unitario)
VALUES 
(1, 1, 100, 1.50),
(1, 2, 150, 0.80),
(2, 3, 200, 1.20),
(3, 4, 100, 0.90),
(4, 5, 250, 1.10);

INSERT INTO Inventario (producto_id, fecha, cantidad_ingreso, cantidad_salida, cantidad_disponible, ubicacion)
VALUES 
(1, '2024-10-01', 200, 50, 150, 'Almacén Central'),
(2, '2024-10-02', 300, 50, 250, 'Almacén Norte'),
(3, '2024-10-03', 150, 20, 130, 'Almacén Sur'),
(4, '2024-10-04', 100, 10, 90, 'Almacén Este'),
(5, '2024-10-05', 250, 30, 220, 'Almacén Oeste');

INSERT INTO Cliente (nombre, contacto, direccion, telefono, email, tipo_cliente)
VALUES 
('Juan Pérez', 'jperez@gmail.com', 'Av. Libertador 123', '123-4567', 'jperez@gmail.com', 'minorista'),
('Empresa ABC', 'contacto@empresaabc.com', 'Zona Comercial 45', '234-5678', 'ventas@empresaabc.com', 'mayorista'),
('María López', 'mlopez@gmail.com', 'Calle 8, Ciudad', '345-6789', 'mlopez@gmail.com', 'minorista'),
('Comercial XYZ', 'xyzcomercial@gmail.com', 'Calle Industria 67', '456-7890', 'contacto@xyzcomercial.com', 'mayorista'),
('Carlos Díaz', 'cdiaz@gmail.com', 'Av. Principal 50', '567-8901', 'cdiaz@gmail.com', 'minorista');

INSERT INTO Venta (cliente_id, fecha_venta, monto_total, estado)
VALUES 
(1, '2024-10-10', 75.00, 'completada'),
(2, '2024-10-12', 150.00, 'pendiente'),
(3, '2024-10-08', 50.00, 'completada'),
(4, '2024-10-09', 200.00, 'pendiente'),
(5, '2024-10-11', 125.00, 'completada');

INSERT INTO Detalle_Venta (venta_id, producto_id, cantidad, precio_unitario)
VALUES 
(1, 1, 20, 1.50),
(2, 2, 50, 0.80),
(3, 3, 30, 1.20),
(4, 4, 40, 0.90),
(5, 5, 25, 1.10);

INSERT INTO Almacen (nombre, ubicacion, capacidad, tipo_producto)
VALUES 
('Almacén Central', 'Centro de la Ciudad', 1000, 'General'),
('Almacén Norte', 'Zona Norte', 500, 'Perecederos'),
('Almacén Sur', 'Zona Sur', 600, 'Bebidas'),
('Almacén Este', 'Zona Este', 700, 'Lácteos'),
('Almacén Oeste', 'Zona Oeste', 800, 'Cereales');

select * from producto;
select * from usuario;
select * from rol;

