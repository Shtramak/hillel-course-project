CREATE SCHEMA IF NOT EXISTS airport;
CREATE TABLE airport_tickets (
  id           INT(10)     NOT NULL AUTO_INCREMENT PRIMARY KEY NOT NULL,
  name         VARCHAR(45) NOT NULL,
  surname      VARCHAR(45) NOT NULL,
  dateOfFlight VARCHAR(45) NOT NULL,
  destCity VARCHAR(45)
);

INSERT INTO airport_tickets VALUES (1, 'Igor', 'Fedotov', '2018-01-01', 'Keln');