CREATE TABLE IF NOT EXISTS education.subject (
  subject_id SERIAL not null primary key,
  name VARCHAR(50) not null,
  descr VARCHAR(250) not null,
  valid  BOOLEAN not null,
  date_of_creation date not null
);

-- Subject insertion
INSERT INTO education.subject(name, descr, valid, date_of_creation) VALUES ('Math', 'Teach how calculate nums', TRUE,
make_date(2000, 5, 15));