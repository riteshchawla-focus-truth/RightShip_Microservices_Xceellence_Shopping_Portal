create table if not exists event_log (
  id bigint auto_increment primary key,
  topic varchar(120) not null,
  payload longtext not null,
  received_at timestamp(6) not null
);
