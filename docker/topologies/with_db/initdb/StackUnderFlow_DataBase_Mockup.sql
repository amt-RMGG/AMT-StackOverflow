DROP SCHEMA IF EXISTS amt_stackunderflow ;

CREATE SCHEMA IF NOT EXISTS amt_stackunderflow DEFAULT CHARACTER SET utf8 ;
USE amt_stackunderflow;

CREATE TABLE IF NOT EXISTS user (
  username VARCHAR(255) NOT NULL,
  email VARCHAR(255) NOT NULL,
  password VARCHAR(255) NOT NULL,
  firstname VARCHAR(255) NOT NULL,
  lastname VARCHAR(255) NOT NULL,
  PRIMARY KEY (username),
  UNIQUE INDEX username_UNIQUE (username ASC) VISIBLE)
ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS question (
  id VARCHAR(255) NOT NULL,
  title VARCHAR(255) NOT NULL,
  text MEDIUMTEXT NOT NULL,
  date DATE NULL,
  author VARCHAR(255) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE INDEX id_UNIQUE (id ASC) VISIBLE,
  INDEX fk_question_user_idx (author ASC) VISIBLE,
  CONSTRAINT fk_question_user
    FOREIGN KEY (author)
    REFERENCES amt_stackunderflow.user (username)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS answer (
  id VARCHAR(255) NOT NULL,
  title VARCHAR(255) NOT NULL,
  text MEDIUMTEXT NOT NULL,
  date DATE NULL,
  user VARCHAR(255) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE INDEX id_UNIQUE (id ASC) VISIBLE,
  INDEX fk_question_user_idx (user ASC) VISIBLE,
  CONSTRAINT fk_question_user0
    FOREIGN KEY (user)
    REFERENCES amt_stackunderflow.user (username)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;



CREATE TABLE IF NOT EXISTS answerVote (
  id INT NOT NULL AUTO_INCREMENT,
  userId VARCHAR(255) NOT NULL,
  answerId VARCHAR(255) NOT NULL,
  PRIMARY KEY (id),
  INDEX fk_vote_user1_idx (userId ASC) VISIBLE,
  INDEX fk_vote_answer1_idx (answerId ASC) VISIBLE,
  CONSTRAINT fk_vote_user1
    FOREIGN KEY (userId)
    REFERENCES amt_stackunderflow.user (username)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_vote_answer1
    FOREIGN KEY (answerId)
    REFERENCES amt_stackunderflow.answer (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS questionVote (
  id INT NOT NULL,
  questionId VARCHAR(255) NOT NULL,
  userId VARCHAR(255) NOT NULL,
  PRIMARY KEY (id),
  INDEX fk_questionVote_question1_idx (questionId ASC) VISIBLE,
  INDEX fk_questionVote_user1_idx (userId ASC) VISIBLE,
  CONSTRAINT fk_questionVote_question1
    FOREIGN KEY (questionId)
    REFERENCES amt_stackunderflow.question (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_questionVote_user1
    FOREIGN KEY (userId)
    REFERENCES amt_stackunderflow.user (username)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- SET SQL_MODE=@OLD_SQL_MODE;
-- SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
-- SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
