create table users(
  username varchar(255) not null primary key,
  password varchar(255) not null,
  enabled boolean not null
);

INSERT INTO users VALUES ('filey_admin', '$2a$10$QKsBY7lVNz9lsKRoKS7H1eJnnuLkvwHcXZ1VzQmdX.sCNkDxBF4O2', true);