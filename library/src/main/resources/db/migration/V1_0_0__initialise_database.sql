CREATE TABLE course
(
  uid   UUID DEFAULT gen_random_uuid() NOT NULL,
  title VARCHAR(1023)                  NOT NULL,
  CONSTRAINT pk_course PRIMARY KEY (uid),
  CONSTRAINT idx_course__name UNIQUE (title)
);

CREATE TABLE professor
(
  uid         UUID DEFAULT gen_random_uuid() NOT NULL,
  firstname   VARCHAR(255)                   NOT NULL,
  middle_name VARCHAR(255),
  lastname    VARCHAR(255)                   NOT NULL,
  CONSTRAINT pk_professor PRIMARY KEY (uid)
);

CREATE TABLE student
(
  uid                  UUID DEFAULT gen_random_uuid() NOT NULL,
  firstname            VARCHAR(255)                   NOT NULL,
  middle_name          VARCHAR(255),
  lastname             VARCHAR(255)                   NOT NULL,
  matriculation_number VARCHAR(7)                     NOT NULL,
  CONSTRAINT pk_student PRIMARY KEY (uid),
  CONSTRAINT idx_student__matriculation_number UNIQUE (matriculation_number)
);

CREATE TABLE professor_holds_course
(
  course_uid    UUID NOT NULL,
  professor_uid UUID NOT NULL,
  CONSTRAINT pk_professor_holds_course PRIMARY KEY (course_uid, professor_uid),
  CONSTRAINT fk_professor_holds_course__course FOREIGN KEY (course_uid) REFERENCES course (uid) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT fk_professor_holds_course__professor FOREIGN KEY (professor_uid) REFERENCES professor (uid) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE student_takes_part_in_course
(
  course_uid  UUID NOT NULL,
  student_uid UUID NOT NULL,
  CONSTRAINT pk_student_take_part_in_course PRIMARY KEY (course_uid, student_uid),
  CONSTRAINT fk_student_take_part_in_course__course FOREIGN KEY (course_uid) REFERENCES course (uid) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT fk_student_take_part_in_course__student FOREIGN KEY (student_uid) REFERENCES student (uid) ON DELETE CASCADE ON UPDATE CASCADE
);
