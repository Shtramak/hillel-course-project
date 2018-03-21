create table IF NOT EXISTS subject (
  subject_id int(10) not null auto_increment primary key,
  name varchar(50) not null,
  descr mediumtext not null,
  valid  bit not null,
  date_of_creation date not null
);

-- Subject insertion
INSERT INTO subject(name, descr, valid, date_of_creation)
VALUES ('Math', 'Teach how calculate nums', TRUE, TO_DATE('15/05/2000', 'DD/MM/YYYY'));