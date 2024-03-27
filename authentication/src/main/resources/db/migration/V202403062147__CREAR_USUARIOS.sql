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
    VALUES (1, 'Daniel', 'Hernández', 'Daniel.Hernandez', 'edgar.hernandez@assisprex.com', '$2a$10$xRyvXCK.pLwSinRTCoBwe.MeoTRj1vDyRo31gJgidzkU5wu9nyMrS', 1),
           (2, 'Santiago', 'Gutierrez', 'Santiago.Gutierrez', 'santiago.gutierrez@assisprex.com', '$2a$10$JurMF0DuZWGQEJGOanQMRu4vBPey/LjIQU2zrr0lowxUu4OqMXbvi', 1),
           (3, 'Manuel', 'Cuellar', 'Manuel.Cuellar', 'manuel.cuellar@assisprex.com', '$2a$10$pk70uPi06FLIX2H./6iaQOUwWeESXgWgE2Zg17bsQgNxABp4dD1Oi', 1),
           (4, 'Andres', 'Vallejo', 'Andres.Vallejo', 'andres.vallejo@assisprex.com', '$2a$10$Sd7DHYZIs0.8mK6wQFOh0e0dySmUve7xkA7inRQErU3jR.95IAczS', 1),
           (5, 'Carlos', 'Molina', 'Carlos.Molina', 'carlos.molina@assisprex.com', '$2a$10$aw8iarSQgxNP3KKruM77DOFUuTrkH1MSQhbM5GKoHw1LsWRp5N98a', 1);
