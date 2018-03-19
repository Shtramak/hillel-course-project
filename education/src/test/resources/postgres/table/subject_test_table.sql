DROP TABLE IF EXISTS education.subject;
create table IF NOT EXISTS education.subject (
  subject_id BIGSERIAL not null primary key,
  name varchar(50) not null,
  descr VARCHAR(250) not null,
  valid  BOOLEAN not null,
  date_of_creation DATE not null
);