CREATE TABLE iF NOT EXISTS customer (
  id              INT(10) PRIMARY KEY NOT NULL,
  name            VARCHAR(64)         NOT NULL,
  surname         VARCHAR(128)        NOT NULL,
  date_of_birth   DATE                NOT NULL,
  phone_number    VARCHAR(24),
  available_funds DECIMAL(15, 2) DEFAULT 0
);

CREATE TABLE iF NOT EXISTS AUTO (
  ID            INT(11)     NOT NULL AUTO_INCREMENT,
  AUTO_BRAND    VARCHAR(20) NOT NULL,
  AUTO_MODEL    VARCHAR(20) NOT NULL,
  MANUFACT_YEAR INT(4)      NOT NULL,
  COUNTRY       VARCHAR(20) NOT NULL,
  PRICE         DECIMAL     NOT NULL,
  PRIMARY KEY (ID)
);

CREATE TABLE iF NOT EXISTS infoSalon (
  ID        INT(11)     NOT NULL AUTO_INCREMENT,
  name      VARCHAR(20) NOT NULL,
  address   VARCHAR(20) NOT NULL,
  telephone VARCHAR(20) NOT NULL,
  PRIMARY KEY (ID)
);