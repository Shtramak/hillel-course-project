-- Students table
CREATE TABLE Student (
  student_id INT PRIMARY KEY,
  firstName VARCHAR(20) NOT NULL,
  lastName VARCHAR(20) NOT NULL,
  student_card_number  VARCHAR(20),
  address VARCHAR(255) NOT NULL
);