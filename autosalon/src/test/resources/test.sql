DROP TABLE IF EXISTS customer, AUTO, infoSalon;

CREATE TABLE customer (
  id              INT(10) PRIMARY KEY NOT NULL,
  name            VARCHAR(64)         NOT NULL,
  surname         VARCHAR(128)        NOT NULL,
  date_of_birth   DATE                NOT NULL,
  phone_number    VARCHAR(24),
  available_funds DECIMAL(15, 2) DEFAULT 0
);

INSERT INTO customer VALUES (1, 'John', 'Rambo', '1946-07-06', '(012)345-67-89', 10000000.50);
INSERT INTO customer VALUES (2, 'John', 'Travolta', '1954-02-18', '(012)345-67-89', 5000000.50);

CREATE TABLE AUTO (
  ID            INT(11)     NOT NULL AUTO_INCREMENT,
  AUTO_BRAND    VARCHAR(20) NOT NULL,
  AUTO_MODEL    VARCHAR(20) NOT NULL,
  MANUFACT_YEAR INT(4)      NOT NULL,
  COUNTRY       VARCHAR(20) NOT NULL,
  PRICE         DECIMAL     NOT NULL,
  PRIMARY KEY (ID)
);


INSERT INTO AUTO (ID, AUTO_BRAND, AUTO_MODEL, MANUFACT_YEAR, COUNTRY, PRICE)
VALUES (1, 'BMW', 'X1', 2012, 'Germany', 100000);
INSERT INTO AUTO (ID, AUTO_BRAND, AUTO_MODEL, MANUFACT_YEAR, COUNTRY, PRICE)
VALUES (2, 'BMW', 'X3', 2013, 'Germany', 200000);
INSERT INTO AUTO (ID, AUTO_BRAND, AUTO_MODEL, MANUFACT_YEAR, COUNTRY, PRICE)
VALUES (3, 'BMW', 'X4', 2014, 'Germany', 300000);
INSERT INTO AUTO (ID, AUTO_BRAND, AUTO_MODEL, MANUFACT_YEAR, COUNTRY, PRICE)
VALUES (4, 'BMW', 'X5', 2015, 'Germany', 400000);
INSERT INTO AUTO (ID, AUTO_BRAND, AUTO_MODEL, MANUFACT_YEAR, COUNTRY, PRICE)
VALUES (5, 'BMW', 'X6', 2016, 'Germany', 500000);

CREATE TABLE infoSalon (
  ID        INT(11)     NOT NULL AUTO_INCREMENT,
  name      VARCHAR(20) NOT NULL,
  address   VARCHAR(20) NOT NULL,
  telephone VARCHAR(20) NOT NULL,
  PRIMARY KEY (ID)
);
INSERT INTO infoSalon (ID, name, address, telephone) VALUES (1, 'Geely', 'China', '00000');
INSERT INTO infoSalon (ID, name, address, telephone) VALUES (2, 'Geely', 'China', '00000');
INSERT INTO infoSalon (ID, name, address, telephone) VALUES (3, 'Geely', 'China', '00000');
INSERT INTO infoSalon (ID, name, address, telephone) VALUES (4, 'Geely', 'China', '00000');
INSERT INTO infoSalon (ID, name, address, telephone) VALUES (5, 'Geely', 'China', '00000');