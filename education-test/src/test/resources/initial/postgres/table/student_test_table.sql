-- Students table
CREATE TABLE education.Student (
  student_id SERIAL PRIMARY KEY,
  firstName VARCHAR(20) NOT NULL,
  lastName VARCHAR(20) NOT NULL,
  student_card_number  VARCHAR(20),
  address VARCHAR(255) NOT NULL
);

INSERT INTO education.Student(firstName, lastName, student_card_number, address)
VALUES ('Givi', 'Trump', '37773','Street 23');