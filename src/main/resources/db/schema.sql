DROP DATABASE lectures;
CREATE DATABASE IF NOT EXISTS lectures;

ALTER DATABASE lectures
  DEFAULT CHARACTER SET utf8
  DEFAULT COLLATE utf8_general_ci;


USE lectures;

CREATE TABLE IF NOT EXISTS lecture_halls (
  id INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(30),
  capacity INT(4) UNSIGNED NOT NULL,
  projector INT(1),
  start_time TIME,
  end_time TIME,
  price INT UNSIGNED,
  INDEX(name)
);

CREATE TABLE IF NOT EXISTS lectures (
  id INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(30),
  lecturer_name VARCHAR(30),
  capacity INT(4) UNSIGNED,
  date DATETIME,
  duration INT(4),
  price INT UNSIGNED,
  lecture_hall_id INT(4) UNSIGNED NOT NULL,
  FOREIGN KEY (lecture_hall_id) REFERENCES lecture_halls(id),
  INDEX(name)
);

CREATE TABLE IF NOT EXISTS visitors (
  id INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(30),
  lecture_id INT(4) UNSIGNED NOT NULL,
  FOREIGN KEY (lecture_id) REFERENCES lectures(id),
  INDEX(name)
);