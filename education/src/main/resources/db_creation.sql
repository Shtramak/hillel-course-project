-- Creating table block
  -- Universities table
CREATE TABLE Universities(
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  nameOfUniversity VARCHAR (100) NOT NULL,
  address VARCHAR (100),
  specialization VARCHAR(75)
);

  -- Students table
CREATE TABLE Student (
  id     INT PRIMARY KEY AUTO_INCREMENT,
  firstName   VARCHAR(20) NOT NULL,
  lastName  VARCHAR(20) NOT NULL,
  uniqueRegistrationNumber  VARCHAR(20),
  address  VARCHAR(255) NOT NULL
);

	-- Subject table
create table Subject (
	id int(10) not null auto_increment primary key,
  name varchar(50) not null,
  descr mediumtext not null,
  valid  bit not null,
  date_of_cretion date not null
);






