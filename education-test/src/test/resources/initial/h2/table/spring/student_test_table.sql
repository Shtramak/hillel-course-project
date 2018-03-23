CREATE TABLE IF NOT EXISTS student (
  student_id INT(10) PRIMARY KEY AUTO_INCREMENT,
  firstName VARCHAR(20) NOT NULL,
  lastName VARCHAR(20) NOT NULL,
  student_card_number  VARCHAR(20),
  address VARCHAR(255) NOT NULL
);

INSERT INTO Student(firstName, lastName, student_card_number, address)
VALUES ('Givi', 'Trump', '37773','Street 23');