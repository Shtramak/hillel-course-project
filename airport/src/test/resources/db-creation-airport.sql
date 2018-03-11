DROP TABLE IF EXISTS airport_tickets, airport;
CREATE TABLE airport(
  id INT (10)NOT NULL AUTO_INCREMENT PRIMARY KEY NOT NULL,
  name VARCHAR (45) NOT NULL,
  date_of_birth VARCHAR (45) NOT NULL,
  phone_number VARCHAR (45) NOT NULL,
  terminal VARCHAR (45) NOT NULL
);