CREATE SCHEMA IF NOT EXISTS `db_java` DEFAULT CHARACTER SET utf8mb4 ;
USE `db_java` ;

-- -----------------------------------------------------
-- Table `db_java`.`users`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `db_java`.`users` ;

CREATE TABLE IF NOT EXISTS `db_java`.`users` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `nickname` VARCHAR(30) NOT NULL,
  `password` VARBINARY(20) NOT NULL,
  `role` ENUM('ADMINISTRATOR', 'NORMAL') NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

CREATE INDEX idx_nickname ON users (nickname);

-- -----------------------------------------------------
-- Table `db_java`.`courses`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `db_java`.`courses` ;

CREATE TABLE IF NOT EXISTS `db_java`.`courses` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `description` VARCHAR(70) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `db_java`.`inscriptions`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `db_java`.`inscriptions` ;

CREATE TABLE IF NOT EXISTS `db_java`.`inscriptions` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(30) NOT NULL,
  `last_name` VARCHAR(50) NOT NULL,
  `cellphone` CHAR(9) NULL,
  `courses_id` INT UNSIGNED NOT NULL,
  `price` DECIMAL(6,2) NOT NULL,
  `registration_date` DATE NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY(courses_id) REFERENCES courses(id) ON UPDATE NO ACTION ON DELETE CASCADE
)
ENGINE = InnoDB;

INSERT INTO users(nickname, `password`, role) VALUES('rene', AES_ENCRYPT('secret', UNHEX('1E51540F')), 'ADMINISTRATOR');

