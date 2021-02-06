DROP TABLE IF EXISTS SL_ORDERS;
DROP TABLE IF EXISTS SL_ITEMS;

CREATE TABLE SL_ORDERS(id int, name varchar(250) NOT NULL, total_amount int);
	
	CREATE TABLE SL_ITEMS(id int AUTO_INCREMENT  PRIMARY KEY, name varchar(250) NOT NULL, 
	order_id int, quantity int, price int, amount int, FOREIGN KEY(order_id) REFERENCES SL_ORDERS(id)
);
	


