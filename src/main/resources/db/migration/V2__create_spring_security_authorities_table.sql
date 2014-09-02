create table authorities (
  username varchar(255) not null,
  authority varchar(255) not null,
  constraint fk_authorities255_users foreign key(username) references users(username));
  create unique index ix_auth_username on authorities (username,authority
);