create table if not exists user_profile (
  user_id varchar(60) primary key,
  email varchar(200),
  full_name varchar(200),
  phone varchar(40),
  created_at timestamp(6) not null
);
