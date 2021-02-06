insert into SL_ORDERS (id,name, total_amount)
values(1, 'order_1', 200);
insert into SL_ITEMS(name, order_id, quantity, price, amount)
	values('parle-g',1, 2, 10,20);
insert into SL_ITEMS(name, order_id, quantity, price, amount)
	values('marigold',1, 1, 10,10);
insert into SL_ITEMS(name, order_id, quantity, price, amount)
	values('rusk',1, 10, 10,100);
insert into SL_ITEMS(name, order_id, quantity, price, amount)
	values('chocobar',1, 2, 20,20);