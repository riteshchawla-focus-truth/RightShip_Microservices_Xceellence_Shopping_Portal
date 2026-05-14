create table if not exists product (
  id bigint auto_increment primary key,
  sku varchar(80) not null unique,
  name varchar(200) not null,
  description text,
  price decimal(12,2) not null,
  currency varchar(3) not null default 'INR',
  available_stock int not null default 0,
  active boolean not null default true,
  created_at timestamp(6) not null
);
