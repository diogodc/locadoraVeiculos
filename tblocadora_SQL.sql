SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';

DROP SCHEMA IF EXISTS `mydb` ;
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`Usuario`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Usuario` ;

CREATE  TABLE IF NOT EXISTS `mydb`.`Usuario` (
  `id_usuario` INT NOT NULL AUTO_INCREMENT ,
  `nome` VARCHAR(45) NOT NULL ,
  `login` VARCHAR(10) NOT NULL ,
  `senha` VARCHAR(10) NOT NULL ,
  PRIMARY KEY (`id_usuario`) ,
  UNIQUE INDEX `login_UNIQUE` (`login` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Cliente`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Cliente` ;

CREATE  TABLE IF NOT EXISTS `mydb`.`Cliente` (
  `id_cliente` INT NOT NULL AUTO_INCREMENT ,
  `cpf_cnpj` VARCHAR(15) NOT NULL ,
  `rg_ie` VARCHAR(20) NOT NULL ,
  `nome` VARCHAR(45) NOT NULL ,
  `telefone` VARCHAR(20) NOT NULL ,
  `endereco` VARCHAR(100) NOT NULL ,
  `obs` TEXT NULL ,
  PRIMARY KEY (`id_cliente`) ,
  UNIQUE INDEX `cpf_cnpj_UNIQUE` (`cpf_cnpj` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Carro`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Carro` ;

CREATE  TABLE IF NOT EXISTS `mydb`.`Carro` (
  `id_carro` INT NOT NULL AUTO_INCREMENT ,
  `placa` VARCHAR(8) NOT NULL ,
  `descricao` VARCHAR(45) NOT NULL ,
  `ano` VARCHAR(4) NOT NULL ,
  `km_atual` INT NOT NULL ,
  `vlr_diaria` DOUBLE NOT NULL ,
  `img` VARCHAR(20) NULL ,
  `obs` TEXT NULL ,
  PRIMARY KEY (`id_carro`) ,
  UNIQUE INDEX `placa_UNIQUE` (`placa` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Aluguel`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Aluguel` ;

CREATE  TABLE IF NOT EXISTS `mydb`.`Aluguel` (
  `id_aluguel` INT NOT NULL ,
  `id_cliente` INT NOT NULL ,
  `id_carro` INT NOT NULL ,
  `dt_inicio` DATETIME NOT NULL ,
  `dt_fim` DATETIME NULL ,
  `dt_devolucao` DATETIME NULL ,
  `vlr_alugel` DOUBLE NOT NULL ,
  `vlr_taxa` DOUBLE NULL ,
  `vlr_total` DOUBLE NULL ,
  PRIMARY KEY (`id_aluguel`) ,
  INDEX `fk_Aluguel_Cliente` (`id_cliente` ASC) ,
  INDEX `fk_Aluguel_Carro1` (`id_carro` ASC) ,
  CONSTRAINT `fk_Aluguel_Cliente`
    FOREIGN KEY (`id_cliente` )
    REFERENCES `mydb`.`Cliente` (`id_cliente` )
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Aluguel_Carro1`
    FOREIGN KEY (`id_carro` )
    REFERENCES `mydb`.`Carro` (`id_carro` )
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Pagamento`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Pagamento` ;

CREATE  TABLE IF NOT EXISTS `mydb`.`Pagamento` (
  `id_pagamento` INT NOT NULL AUTO_INCREMENT ,
  `id_aluguel` INT NOT NULL ,
  `forma_pg` INT NOT NULL ,
  `vlr_pago` DOUBLE NOT NULL ,
  PRIMARY KEY (`id_pagamento`) ,
  INDEX `fk_Pagamento_Aluguel1` (`id_aluguel` ASC) ,
  CONSTRAINT `fk_Pagamento_Aluguel1`
    FOREIGN KEY (`id_aluguel` )
    REFERENCES `mydb`.`Aluguel` (`id_aluguel` )
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
