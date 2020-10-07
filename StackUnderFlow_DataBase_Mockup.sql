DROP SCHEMA IF EXISTS AMT_STACKUNDERFLOW;
CREATE SCHEMA IF NOT EXISTS `AMT_STACKUNDERFLOW` DEFAULT CHARACTER SET utf8mb4 ;
USE `AMT_STACKUNDERFLOW` ;


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


insert into user (username,email,password,firstname, lastname)
values("momo","momo@momo.mo","PASSWORD", "Maurice", "Lehmann");