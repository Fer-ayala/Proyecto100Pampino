create database inventarioPampinoFinal;
use inventariopampinofinal;

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

select * from rol;