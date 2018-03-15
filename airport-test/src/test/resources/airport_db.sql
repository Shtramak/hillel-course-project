DROP TABLE IF EXISTS airport;

CREATE TABLE airport (
  id            INT(10)     NOT NULL AUTO_INCREMENT PRIMARY KEY NOT NULL,
  name          VARCHAR(45) NOT NULL,
  date_of_birth VARCHAR(45) NOT NULL,
  phone_number  VARCHAR(45) NOT NULL,
  terminal      VARCHAR(45) NOT NULL
);

INSERT INTO airport VALUES (3, 'Borispil', '1995-2-20', '(044)666-66-66', 'D');
INSERT INTO airport VALUES (2, 'Zhuliani', '2012-1-01', '(044)333-33-33', 'A');