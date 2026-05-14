create table if not exists payment (
  id bigint auto_increment primary key,
  order_id bigint not null,
  amount decimal(12,2) not null,
  currency varchar(3) not null,
  status varchar(20) not null,
  provider_ref varchar(200),
  created_at timestamp(6) not null,
  updated_at timestamp(6) not null
);

create table if not exists outbox_event (
  id bigint auto_increment primary key,
  event_id varchar(80) not null unique,
  aggregate_type varchar(60) not null,
  aggregate_id varchar(80) not null,
  event_type varchar(120) not null,
  topic varchar(120) not null,
  msg_key varchar(120),
  payload longtext not null,
  status varchar(20) not null,
  attempts int not null,
  last_error text,
  available_at timestamp(6) not null,
  created_at timestamp(6) not null,
  sent_at timestamp(6),
  version bigint not null
);
