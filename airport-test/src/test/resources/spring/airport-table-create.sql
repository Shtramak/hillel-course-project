CREATE SCHEMA IF NOT EXISTS airport;
CREATE TABLE airport_airports (
  id            INT(10)     NOT NULL AUTO_INCREMENT PRIMARY KEY NOT NULL,
  name          VARCHAR(45) NOT NULL,
  date_of_birth VARCHAR(45) NOT NULL,
  terminal      VARCHAR(45) NOT NULL,
  phone_number  VARCHAR(45)
);

INSERT INTO airport_airports VALUES (1, 'Borysbil', '1990-01-01', 'D', '04466666666');