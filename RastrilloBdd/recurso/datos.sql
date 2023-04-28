INSERT INTO producto (nombre_categoria, nombre, descripcion, precio, stock_cantidad) VALUES ('Gama marron', 'Reproductor de música', 'Panasonic SC-PM250 - Microcadena (Hi- Fi, Bluetooth, Equipo De Sonido Para Tu Hogar, CD, Bluetooth, USB, MP3, Radio FM, 20W (RMS),Diseño compacto, Ecualizador)-Color Plata ', 599.99, 100);
INSERT INTO producto (nombre_categoria, nombre, descripcion, precio, stock_cantidad) VALUES ('Gama blanca', 'Microondas', 'Acero inoxidable. Con grill. Sistema de distribución de ondas de generación constante. Selector de funciones: microondas (5 niveles), grill y microondas + grill.', 129.99, 50);
INSERT INTO producto (nombre_categoria, nombre, descripcion, precio, stock_cantidad) VALUES ('Gama PAE', 'Aspiradora', 'Una aspiradora de alta potencia con filtro HEPA y varios accesorios', 249.99, 25);
INSERT INTO producto (nombre_categoria, nombre, descripcion, precio, stock_cantidad) VALUES ('Gama PAE', 'Cafetera', 'Una cafetera automática con capacidad para 10 tazas y varias opciones de preparación', 79.99, 75);
INSERT INTO producto (nombre_categoria, nombre, descripcion, precio, stock_cantidad) VALUES ('Mubles', 'Mesa', 'Mesa de comedor de madera de maciza de sheesham L.160', 89.99, 150);


INSERT INTO cliente (nombre, dni) VALUES ('Juan Pérez', '12345678A');
INSERT INTO cliente (nombre, dni) VALUES ('María González', '87654321B');
INSERT INTO cliente (nombre, dni) VALUES ('Luis García', '45678912C');
INSERT INTO cliente (nombre, dni) VALUES ('Ana Martínez', '65432198D');
INSERT INTO cliente (nombre, dni) VALUES ('Pedro Sánchez', '78965432E');


INSERT INTO pedido (pago, fecha, id_cliente) VALUES (599.99, '2023-03-28', 1);
INSERT INTO pedido (pago, fecha, id_cliente) VALUES (249.99, '2023-03-27', 2);
INSERT INTO pedido (pago, fecha, id_cliente) VALUES (89.99, '2023-03-26', 3);
INSERT INTO pedido (pago, fecha, id_cliente) VALUES (1299.99, '2023-03-25', 4);
INSERT INTO pedido (pago, fecha, id_cliente) VALUES (79.99, '2023-03-24', 5);



