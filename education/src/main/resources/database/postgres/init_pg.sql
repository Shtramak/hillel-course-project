-- Creating table block
  -- Universities table
CREATE TABLE IF NOT EXISTS Universities(
  univer_id INT(10) NOT NULL AUTO_INCREMENT PRIMARY KEY ,
  name_of_university VARCHAR (100) NOT NULL,
  address VARCHAR (100),
  specialization VARCHAR(75)
);

  -- Students table
CREATE TABLE IF NOT EXISTS Student (
  student_id INT(10) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  firstName VARCHAR(20) NOT NULL,
  lastName VARCHAR(20) NOT NULL,
  student_card_number  VARCHAR(20),
  address VARCHAR(255) NOT NULL
);

	-- Subject table
create table IF NOT EXISTS Subject (
	subject_id INT(10) not null AUTO_INCREMENT primary key,
  name varchar(50) not null,
  descr VARCHAR(255) not null,
  valid BIT not null,
  date_of_creation date not null
);






