INSERT INTO region (name) VALUES ('Santiago');

INSERT INTO municipality (name, address, region_id) VALUES ('Puente Alto', 'concha y toro 123', 1);
INSERT INTO municipality (name, address, region_id) VALUES ('Pirque', 'avenida pirque 123', 1);
INSERT INTO municipality (name, address, region_id) VALUES ('La Florida', 'AV. la florida 123', 1);

INSERT INTO users (user_uuid, user_name,name,email,address,phone,is_active)
VALUES ('a1b2c3d4-0000-0000-0000-000000000001', 'admin','User admin test','emailtest@test.com','house test','+56911111111',true);

INSERT INTO user_municipality (user_id, muni_id) VALUES (1, 1);
INSERT INTO user_municipality (user_id, muni_id) VALUES (1, 2);