create table if not exists cart (
  id bigint auto_increment primary key,
  user_id varchar(60) not null,
  status varchar(20) not null,
  created_at timestamp(6) not null,
  updated_at timestamp(6) not null
);

create table if not exists cart_item (
  id bigint auto_increment primary key,
  cart_id bigint not null,
  product_id bigint not null,
  quantity int not null,
  unit_price decimal(12,2) not null,
  currency varchar(3) not null,
  constraint fk_cart_item_cart foreign key (cart_id) references cart(id) on delete cascade,
  unique key uk_cart_product (cart_id, product_id)
);
