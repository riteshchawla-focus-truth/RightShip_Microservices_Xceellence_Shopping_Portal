insert into product(sku,name,description,price,currency,available_stock,active,created_at)
values
('SKU-1','T-Shirt','Cotton T-Shirt',499.00,'INR',100,true,now()),
('SKU-2','Shoes','Running shoes',1999.00,'INR',50,true,now())
on duplicate key update name=values(name);
