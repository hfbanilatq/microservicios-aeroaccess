CREATE SCHEMA IF NOT EXISTS `aeroaccess_db`;
USE `aeroaccess_db`;

-- -----------------------------------------------------
-- Table `aeroaccess_db`.`hibernate_sequence`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `aeroaccess_db`.`hibernate_sequence`;
CREATE TABLE IF NOT EXISTS `aeroaccess_db`.`hibernate_sequence`
(
    `next_val` BIGINT NOT NULL
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;

-- -----------------------------------------------------
-- Insert `aeroaccess_db`.`hibernate_sequence`
-- -----------------------------------------------------
INSERT INTO `aeroaccess_db`.`hibernate_sequence`(next_val) VALUES (1);

-- -----------------------------------------------------
-- Table `aeroaccess_db`.`rol_usuario`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `aeroaccess_db`.`rol_usuario`;
CREATE TABLE IF NOT EXISTS `aeroaccess_db`.`rol_usuario`
(
    `id`         BIGINT      NOT NULL AUTO_INCREMENT,
    `nombre_rol` VARCHAR(45) DEFAULT NULL,
    PRIMARY KEY (`id`)
    )
    ENGINE = InnoDB
    AUTO_INCREMENT = 1
    DEFAULT CHARSET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;

-- -----------------------------------------------------
-- Table `aeroaccess_db`.`usuario`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `aeroaccess_db`.`usuario`;
CREATE TABLE IF NOT EXISTS `aeroaccess_db`.`usuario`
(
    `id`             BIGINT       NOT NULL AUTO_INCREMENT,
    `nombre`         VARCHAR(64)  DEFAULT NULL,
    `apellido`       VARCHAR(64)  DEFAULT NULL,
    `nombre_usuario` VARCHAR(45)  NOT NULL,
    `correo`         VARCHAR(255) DEFAULT NULL,
    `contrasenia`    VARCHAR(255) NOT NULL,
    `es_activo`      TINYINT(1)   DEFAULT NULL,
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB
    AUTO_INCREMENT = 1
    DEFAULT CHARSET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;

-- -----------------------------------------------------
-- Table `aeroaccess_db`.`usuario_rol_usuario`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `aeroaccess_db`.`usuario_rol_usuario`;
CREATE TABLE IF NOT EXISTS `aeroaccess_db`.`usuario_rol_usuario`
(
    `usuario_id`     BIGINT NOT NULL,
    `rol_usuario_id` BIGINT NOT NULL,
    PRIMARY KEY (`usuario_id`, `rol_usuario_id`),
    KEY `fk_usuario_rol_usuario_has_usuario` (`usuario_id`),
    KEY `fk_usuario_rol_usuario_has_rol_usuario` (`rol_usuario_id`),
    CONSTRAINT `fk_usuario_rol_usuario_has_usuario` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`),
    CONSTRAINT `fk_usuario_rol_usuario_has_rol_usuario` FOREIGN KEY (`rol_usuario_id`) REFERENCES `rol_usuario` (`id`)
)
    ENGINE = InnoDB
    AUTO_INCREMENT = 1
    DEFAULT CHARSET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;

