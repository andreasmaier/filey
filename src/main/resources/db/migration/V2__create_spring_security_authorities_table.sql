create table authorities (
  id serial primary key,
  username varchar(255) not null,
  authority varchar(255) not null,
  constraint fk_authorities_users foreign key(username) references users(username)
);

INSERT INTO authorities VALUES (0, 'filey_admin', 'ROLE_ADMIN');