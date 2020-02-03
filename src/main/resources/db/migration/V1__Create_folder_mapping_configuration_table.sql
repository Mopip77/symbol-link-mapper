create table folder_mapping_configuration
(
	id int auto_increment,
	source_path varchar(200) not null,
	dest_path varchar(200) not null,
	excluded_regx varchar(200) null,
	auto_re_mapping bool default false null,
	auto_re_mapping_period int default 3 null,
	gmt_last_mapping long null,
	symbol_link_type int not null,
	constraint folder_mapping_configuration_pk
		primary key (id)
);