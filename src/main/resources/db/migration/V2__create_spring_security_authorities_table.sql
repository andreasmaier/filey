create table authorities (
  id integer not null primary key,
  username varchar(255) not null,
  authority varchar(255) not null,
  constraint fk_authorities_users foreign key(username) references users(username));
  create unique index ix_auth_username on authorities (username,authority
);

INSERT INTO authorities VALUES (1, 'filey_admin', 'ROLE_ADMIN');