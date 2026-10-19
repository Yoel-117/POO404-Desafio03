CREATE DATABASE IF NOT EXISTS bibliotecaudb CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE bibliotecaudb;

DROP TABLE IF EXISTS prestamos;
DROP TABLE IF EXISTS libros;
DROP TABLE IF EXISTS estudiantes;
DROP TABLE IF EXISTS categorias;

CREATE TABLE categorias (
    id_categoria INT(11) PRIMARY KEY AUTO_INCREMENT,
    nombre_categoria VARCHAR(80) NOT NULL
) ENGINE=InnoDB;

CREATE TABLE libros (
    id_libro INT(11) PRIMARY KEY AUTO_INCREMENT,
    titulo VARCHAR(150) NOT NULL,
    autor VARCHAR(100) NOT NULL,
    isbn VARCHAR(20) UNIQUE,
    id_categoria INT(11),
    cantidad_disponible INT(11) DEFAULT 1,
    CONSTRAINT fk_libro_categoria FOREIGN KEY (id_categoria) REFERENCES categorias(id_categoria)
) ENGINE=InnoDB;

CREATE TABLE estudiantes (
    id_estudiante INT(11) PRIMARY KEY AUTO_INCREMENT,
    carnet VARCHAR(10) UNIQUE NOT NULL,
    nombre_estudiante VARCHAR(100) NOT NULL,
    carrera VARCHAR(80) NOT NULL,
    telefono VARCHAR(9)
) ENGINE=InnoDB;

CREATE TABLE prestamos (
    id_prestamo INT(11) PRIMARY KEY AUTO_INCREMENT,
    id_estudiante INT(11) NOT NULL,
    id_libro INT(11) NOT NULL,
    fecha_prestamo DATE NOT NULL,
    fecha_devolucion DATE NOT NULL,
    estado VARCHAR(20) DEFAULT 'Activo',
    CONSTRAINT fk_prestamo_estudiante FOREIGN KEY (id_estudiante) REFERENCES estudiantes(id_estudiante),
    CONSTRAINT fk_prestamo_libro FOREIGN KEY (id_libro) REFERENCES libros(id_libro)
) ENGINE=InnoDB;

INSERT INTO categorias (nombre_categoria) VALUES ('Programación'), ('Bases de datos'), ('Literatura'), ('Matemática');
INSERT INTO libros (titulo, autor, isbn, id_categoria, cantidad_disponible) VALUES
('Clean Code', 'Robert C. Martin', '9780132350884', 1, 3),
('Fundamentos de bases de datos', 'Abraham Silberschatz', '9788448190330', 2, 2),
('Cien años de soledad', 'Gabriel García Márquez', '9780307474728', 3, 1);
INSERT INTO estudiantes (carnet, nombre_estudiante, carrera, telefono) VALUES
('SMSS001', 'Estudiante de Prueba', 'Ingeniería en Computación', '70000000');
