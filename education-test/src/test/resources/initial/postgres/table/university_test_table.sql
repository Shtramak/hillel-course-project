DROP TABLE IF EXISTS Universities;
CREATE TABLE Universities (
  univer_id SERIAL NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name_of_university VARCHAR (100) NOT NULL,
  address VARCHAR (100),
  specialization VARCHAR(75)
);

INSERT INTO education.Universities(name_of_university, address, specialization) VALUES
('KPI', 'pr.Peremogy', 'technical');