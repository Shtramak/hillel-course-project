-- Creating table block
  -- Universities table
CREATE TABLE Universities(
  univer_id SERIAL NOT NULL PRIMARY KEY,
  name_of_university VARCHAR (100) NOT NULL,
  address VARCHAR (100),
  specialization VARCHAR(75)
);

  -- Students table
CREATE TABLE Student (
  student_id INT NOT NULL PRIMARY KEY,
  firstName VARCHAR(20) NOT NULL,
  lastName VARCHAR(20) NOT NULL,
  student_card_number  VARCHAR(20),
  address VARCHAR(255) NOT NULL
);

	-- Subject table
create table Subject (
	subject_id SERIAL not null primary key,
  name varchar(50) not null,
  descr VARCHAR(255) not null,
  valid  BOOLEAN not null,
  date_of_creation date not null
);






