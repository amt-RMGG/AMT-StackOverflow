DROP SCHEMA IF EXISTS amt_stackunderflow;
CREATE SCHEMA IF NOT EXISTS `amt_stackunderflow` DEFAULT CHARACTER SET utf8mb4 ;
USE `amt_stackunderflow` ;
SET GLOBAL time_zone = '+00:00';


CREATE TABLE IF NOT EXISTS user (
  `username` VARCHAR(255) NOT NULL,
  `email` VARCHAR(255) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  `firstname` VARCHAR(255) NOT NULL,
  `lastname` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`username`)
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;

CREATE TABLE IF NOT EXISTS question (
  `id` VARCHAR (255) NOT NULL,
  `title` VARCHAR(255) NOT NULL,
  `text` VARCHAR(255) NOT NULL,
  `author` VARCHAR(255) NOT NULL,

  PRIMARY KEY (`id`)
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;


insert into user (username,email,password,firstname, lastname)
values("momo","momo@momo.mo","PASSWORD", "Maurice", "Lehmann");


-- insert into question (title,text,author)
-- values("Quel est le sens de la vie?","Grosse question...","momo");