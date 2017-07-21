create table submission (
	id uuid PRIMARY KEY,
	uploadtime timestamp not null,
	file_contents bytea not null,
	file_name text not null,
	student_id varchar(255),
	assignment_id varchar(255)
);