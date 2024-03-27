USE `aeroaccess_db`;

-- -----------------------------------------------------
-- Table `aeroaccess_db`.`rol_usuario`
-- -----------------------------------------------------
INSERT INTO `aeroaccess_db`.`rol_usuario`
VALUES (1, 'ROLE_ADMIN'),
       (2, 'ROLE_SUPERVISOR'),
       (3, 'ROLE_AGENT'),
       (4, 'ROLE_USER');

-- -----------------------------------------------------
-- Table `aeroaccess_db`.`usuario`
-- -----------------------------------------------------
INSERT INTO `aeroaccess_db`.`usuario`
    VALUES (1, 'Daniel', 'Hernández', 'Daniel.Hernandez', 'edgar.hernandez@assisprex.com', '$2a$10$itYKrqPWEWFfzPX95ARWF.0zbc7NwtuGes878h9MN/9nmVbPlj6KG', 1),
           (2, 'Santiago', 'Gutierrez', 'Santiago.Gutierrez', 'santiago.gutierrez@assisprex.com', '$2a$10$itYKrqPWEWFfzPX95ARWF.0zbc7NwtuGes878h9MN/9nmVbPlj6KG', 1),
           (3, 'Manuel', 'Cuellar', 'Manuel.Cuellar', 'manuel.cuellar@assisprex.com', '$2a$10$itYKrqPWEWFfzPX95ARWF.0zbc7NwtuGes878h9MN/9nmVbPlj6KG', 1),
           (4, 'Andres', 'Vallejo', 'Andres.Vallejo', 'andres.vallejo@assisprex.com', '$2a$10$itYKrqPWEWFfzPX95ARWF.0zbc7NwtuGes878h9MN/9nmVbPlj6KG', 1),
           (5, 'Carlos', 'Molina', 'Carlos.Molina', 'carlos.molina@assisprex.com', '$2a$10$itYKrqPWEWFfzPX95ARWF.0zbc7NwtuGes878h9MN/9nmVbPlj6KG', 1);

INSERT INTO `aeroaccess_db`.`usuario_rol_usuario`
    VALUES(1,1);
