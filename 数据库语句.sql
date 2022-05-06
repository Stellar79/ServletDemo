create table Course
(
	id INT(11) AUTO_INCREMENT, 
  lastname    VARCHAR(20) null,
  firstname    varchar(20) null,
	mi VARCHAR(10) NULL,
	email VARCHAR(100) NULL,
	city VARCHAR(20) NULL,
	state VARCHAR(20) null,
	zip VARCHAR(20) NULL,
	telephone VARCHAR(20) NULL,
	street VARCHAR(500) NULL,
  constraint Course_id_uindex PRIMARY KEY (id),
  unique (id)
)
  engine = InnoDB;