package com.sl.ms.ordermanagement.dto;

import java.util.List;

import com.sl.ms.ordermanagement.sql.domain.Item;
import com.sl.ms.ordermanagement.sql.domain.Order;

public class OrderDetails {
	
	private Order order;
	private List<Item> items;
	
	public OrderDetails() {
	}
	public OrderDetails(Order order, List<Item> items) {
		this.order = order;
		this.items = items;
	}
	
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public List<Item> getItems() {
		return items;
	}
	public void setItems(List<Item> items) {
		this.items = items;
	}

	@Override
	public String toString() {
		return "OrderDetails [order=" + order + ", items=" + items + "]";
	}
	
	

}
