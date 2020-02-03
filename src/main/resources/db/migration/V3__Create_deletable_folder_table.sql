create table deletable_folder
(
	id int auto_increment,
	path varchar(200) not null,
	gmt_create datetime default now() null,
	constraint deletable_folder_pk
		primary key (id)
);

create unique index deletable_folder_path_uindex
	on deletable_folder (path);



