-- Insertar comunas iniciales
INSERT INTO commune (name, region) VALUES ('Santiago', 13);
INSERT INTO commune (name, region) VALUES ('Valparaíso', 5);
INSERT INTO commune (name, region) VALUES ('Concepción', 8);

-- Insertar servicios municipales
INSERT INTO services (name, description) VALUES ('book-api', 'Descuento del 30% en libros');
INSERT INTO services (name, description) VALUES ('bike-api', 'Acceso a bicicletas municipales');