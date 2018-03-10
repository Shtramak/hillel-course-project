DROP TABLE IF EXISTS airport_tickets, airport;
CREATE TABLE airport_tickets(
  id INT(10) NOT NULL AUTO_INCREMENT PRIMARY KEY NOT NULL,
  name VARCHAR (45) NOT NULL,
  surname VARCHAR (45) NOT NULL,
  dateOfFlight VARCHAR(45) NOT NULL,
  destCity VARCHAR(45)
);

CREATE TABLE airport(
  id INT (10)NOT NULL AUTO_INCREMENT PRIMARY KEY NOT NULL,
  name VARCHAR (45) NOT NULL,
  date_of_birth VARCHAR (45) NOT NULL,
  phone_number VARCHAR (45) NOT NULL,
  terminal VARCHAR (45) NOT NULL
);

-- insert into airport_tickets(id, name, surname, dateOfFlight, destCity)values(1,'Igor', 'Kuzii', '2018/01/01', 'Keln');
