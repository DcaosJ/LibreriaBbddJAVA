drop database if exists Rastrillo;
create database Rastrillo CHARACTER SET utf8mb4; ;
use Rastrillo;



CREATE TABLE producto (
  id_articulo INT AUTO_INCREMENT PRIMARY KEY,
  nombre_categoria VARCHAR(50) NOT NULL,
  nombre VARCHAR(50) UNIQUE NOT NULL,  
  descripcion TEXT,
  precio DECIMAL(5,2) NOT NULL CHECK (precio >= 0.01),
  stock_cantidad INT DEFAULT 0 CHECK (stock_cantidad >= 0),  
); 

CREATE TABLE cliente (
  id_cliente INT AUTO_INCREMENT PRIMARY KEY,
  nombre VARCHAR(50) NOT NULL,
  dni VARCHAR(9) NOT NULL
);

CREATE TABLE pedido (
  id_pedido INT AUTO_INCREMENT PRIMARY KEY,
  nombre_producto VARCHAR(50), 
  pago DECIMAL(7,2) NOT NULL CHECK (pago >= 0),  
  fecha DATE NOT NULL,
  id_cliente INT NOT NULL,
  FOREIGN KEY (id_cliente) REFERENCES cliente(id_cliente),
  FOREIGN key (nombre_producto) REFERENCES producto(nombre)
);
