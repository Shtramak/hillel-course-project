CREATE TABLE Student (
  student_id INT PRIMARY KEY AUTO_INCREMENT,
  firstName VARCHAR(20) NOT NULL,
  lastName VARCHAR(20) NOT NULL,
  student_card_number  VARCHAR(20),
  address VARCHAR(255) NOT NULL
);