CREATE SCHEMA IF NOT EXISTS education;
DROP TABLE IF EXISTS Universities;
CREATE TABLE IF NOT EXISTS universities(
  univer_id INT (10) NOT NULL AUTO_INCREMENT,
  name_of_university VARCHAR (100) NOT NULL,
  address VARCHAR (100),
  specialization VARCHAR(75),
  PRIMARY KEY(univer_id)
);

INSERT INTO universities(univer_id, name_of_university, address, specialization)
VALUES (1,'KPI', 'pr.Peremogy', 'technical');