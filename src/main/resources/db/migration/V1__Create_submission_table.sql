create table submission (
	id uuid PRIMARY KEY,
	uploadtime timestamp not null,
	file_contents bytea not null,
	file_name text not null,
	student_id varchar(255) not null,
	assignment_id varchar(255) not null
	score integer, 
	total integer,
	grader_output text;
);