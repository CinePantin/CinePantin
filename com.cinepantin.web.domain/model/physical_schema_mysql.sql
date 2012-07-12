SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';

CREATE SCHEMA IF NOT EXISTS `desEssais` DEFAULT CHARACTER SET utf8 ;
USE `desEssais` ;

-- -----------------------------------------------------
-- Table `desEssais`.`Articles`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `desEssais`.`Articles` ;

CREATE  TABLE IF NOT EXISTS `desEssais`.`Articles` (
  `idArticle` INT NOT NULL AUTO_INCREMENT ,
  `join_table` VARCHAR(31) NOT NULL ,
  PRIMARY KEY (`idArticle`) ,
  UNIQUE INDEX `idArticle_UNIQUE` (`idArticle` ASC) ,
  INDEX `idArticle_INDEX` (`idArticle` ASC) )
ENGINE = InnoDB
COMMENT = '<* implements Article> JPA Entity table';


-- -----------------------------------------------------
-- Table `desEssais`.`Books`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `desEssais`.`Books` ;

CREATE  TABLE IF NOT EXISTS `desEssais`.`Books` (
  `idArticle` INT NOT NULL ,
  `Title` VARCHAR(45) NULL ,
  `Author` VARCHAR(45) NULL ,
  PRIMARY KEY (`idArticle`) ,
  UNIQUE INDEX `idArticle_UNIQUE` (`idArticle` ASC) ,
  INDEX `id_Article` (`idArticle` ASC) ,
  CONSTRAINT `id_Article`
    FOREIGN KEY (`idArticle` )
    REFERENCES `desEssais`.`Articles` (`idArticle` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
