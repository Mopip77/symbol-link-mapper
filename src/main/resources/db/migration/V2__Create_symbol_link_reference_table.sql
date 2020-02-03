create table symbol_link_reference
(
    id int auto_increment,
    source_path varchar(200) not null,
    dest_path varchar(200) not null,
    type int default 0 not null comment 'symbol link type
0 -> direct symbol link
1 -> recursive symbol link
',
    is_folder bool not null,
    gmt_create datetime default now() null,
    constraint symbol_link_reference_pk
        primary key (id)
);

create index symbol_link_reference_source_path_index
    on symbol_link_reference (source_path);

create unique index symbol_link_reference_dest_path_uindex
    on symbol_link_reference (dest_path);