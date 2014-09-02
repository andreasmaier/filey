create table users(
  username varchar(255) not null primary key,
  password varchar(255) not null,
  enabled boolean not null
);