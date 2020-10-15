-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`users`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`users` ;

CREATE TABLE IF NOT EXISTS `mydb`.`users` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `login` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `blocked` TINYINT NOT NULL DEFAULT 0,
  UNIQUE INDEX `login_UNIQUE` (`login` ASC) VISIBLE,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`accounts`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`accounts` ;

CREATE TABLE IF NOT EXISTS `mydb`.`accounts` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `card_number` VARCHAR(16) NOT NULL,
  `name` VARCHAR(45) NOT NULL COMMENT 'Name set by user to easier manage his accounts',
  `amount` INT NOT NULL,
  `currency` ENUM('UAH', 'USD', 'EUR') NOT NULL,
  `user_id` INT NOT NULL,
  `blocked` TINYINT NOT NULL DEFAULT 0,
  INDEX `fk_accounts_users1_idx` (`user_id` ASC) VISIBLE,
  UNIQUE INDEX `card_number_UNIQUE` (`card_number` ASC) VISIBLE,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_accounts_users1`
    FOREIGN KEY (`user_id`)
    REFERENCES `mydb`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`payments`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`payments` ;

CREATE TABLE IF NOT EXISTS `mydb`.`payments` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `from_account` INT NOT NULL,
  `to_account` INT NOT NULL,
  `amount` INT NOT NULL,
  `status` ENUM('READY', 'SENT', 'DECLINED') NOT NULL,
  `time` DATETIME NOT NULL,
  INDEX `fk_accounts_has_accounts_accounts2_idx` (`to_account` ASC) VISIBLE,
  INDEX `fk_accounts_has_accounts_accounts1_idx` (`from_account` ASC) VISIBLE,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_accounts_has_accounts_accounts1`
    FOREIGN KEY (`from_account`)
    REFERENCES `mydb`.`accounts` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_accounts_has_accounts_accounts2`
    FOREIGN KEY (`to_account`)
    REFERENCES `mydb`.`accounts` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`admins`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`admins` ;

CREATE TABLE IF NOT EXISTS `mydb`.`admins` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `login` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  UNIQUE INDEX `login_UNIQUE` (`login` ASC) VISIBLE,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`templates`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`templates` ;

CREATE TABLE IF NOT EXISTS `mydb`.`templates` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `from_account` INT NOT NULL,
  `to_account` INT NOT NULL,
  `amount` INT NOT NULL,
  INDEX `fk_templates_accounts1_idx` (`from_account` ASC) VISIBLE,
  PRIMARY KEY (`id`),
  INDEX `fk_templates_accounts2_idx` (`to_account` ASC) VISIBLE,
  CONSTRAINT `fk_templates_accounts1`
    FOREIGN KEY (`from_account`)
    REFERENCES `mydb`.`accounts` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_templates_accounts2`
    FOREIGN KEY (`to_account`)
    REFERENCES `mydb`.`accounts` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`unblock_request`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`unblock_request` ;

CREATE TABLE IF NOT EXISTS `mydb`.`unblock_request` (
  `accounts_id` INT NOT NULL,
  `changed_by` INT NULL,
  `considered` TINYINT NULL,
  INDEX `fk_payments_has_admins_admins1_idx` (`changed_by` ASC) VISIBLE,
  PRIMARY KEY (`accounts_id`),
  CONSTRAINT `fk_payments_has_admins_admins1`
    FOREIGN KEY (`changed_by`)
    REFERENCES `mydb`.`admins` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_unblock_request_accounts1`
    FOREIGN KEY (`accounts_id`)
    REFERENCES `mydb`.`accounts` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `mydb`.`users`
-- -----------------------------------------------------
START TRANSACTION;
USE `mydb`;
INSERT INTO `mydb`.`users` (`id`, `login`, `password`, `blocked`) VALUES (DEFAULT, 'login1@mail', 'Password1', 0);
INSERT INTO `mydb`.`users` (`id`, `login`, `password`, `blocked`) VALUES (DEFAULT, 'login2@mail', 'password2', 0);

COMMIT;


-- -----------------------------------------------------
-- Data for table `mydb`.`accounts`
-- -----------------------------------------------------
START TRANSACTION;
USE `mydb`;
INSERT INTO `mydb`.`accounts` (`id`, `card_number`, `name`, `amount`, `currency`, `user_id`, `blocked`) VALUES (DEFAULT, '1234124312341234', 'name1', 1234, 'EUR', 1, 0);
INSERT INTO `mydb`.`accounts` (`id`, `card_number`, `name`, `amount`, `currency`, `user_id`, `blocked`) VALUES (DEFAULT, '4321432141234122', 'name2', 4321, 'EUR', 2, 0);

COMMIT;


-- -----------------------------------------------------
-- Data for table `mydb`.`payments`
-- -----------------------------------------------------
START TRANSACTION;
USE `mydb`;
INSERT INTO `mydb`.`payments` (`id`, `from_account`, `to_account`, `amount`, `status`, `time`) VALUES (DEFAULT, 2, 1, 123, 'SENT', NOW());

COMMIT;


-- -----------------------------------------------------
-- Data for table `mydb`.`admins`
-- -----------------------------------------------------
START TRANSACTION;
USE `mydb`;
INSERT INTO `mydb`.`admins` (`id`, `login`, `password`) VALUES (DEFAULT, 'admin', 'admin');

COMMIT;


-- -----------------------------------------------------
-- Data for table `mydb`.`templates`
-- -----------------------------------------------------
START TRANSACTION;
USE `mydb`;
INSERT INTO `mydb`.`templates` (`id`, `from_account`, `to_account`, `amount`) VALUES (DEFAULT, 1, 2, 34);

COMMIT;


-- -----------------------------------------------------
-- Data for table `mydb`.`unblock_request`
-- -----------------------------------------------------
START TRANSACTION;
USE `mydb`;
INSERT INTO `mydb`.`unblock_request` (`accounts_id`, `changed_by`, `considered`) VALUES (DEFAULT, 1, 0);

COMMIT;

