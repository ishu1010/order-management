package com.sl.ms.ordermanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sl.ms.ordermanagement.dao.ItemsRepository;
import com.sl.ms.ordermanagement.dao.OrderRepository;
import com.sl.ms.ordermanagement.dto.OrderDetails;
import com.sl.ms.ordermanagement.sql.domain.Item;
import com.sl.ms.ordermanagement.sql.domain.Order;

@Service
public class OrderManagementService {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private ItemsRepository itemsRepository;

	public List<Order> getAllOrders() {
		return orderRepository.findAll();
	}

	public OrderDetails getOrderDetails(Integer orderId) {
		Order order = orderRepository.findById(orderId).orElseThrow(RuntimeException::new);

		List<Item> itemList = itemsRepository.findByOrderId(orderId);
		return new OrderDetails(order, itemList);
	}

	public String saveOrderByOrderId(Integer orderId, OrderDetails details) {
		Order order = details.getOrder();
		order.setId(orderId);
		orderRepository.save(order);

		details.getItems().forEach(item -> {
			item.setOrderId(orderId);
			itemsRepository.save(item);
		});
		return "order saved successfully!";
	}

	public String deleteOrderByOrderId(Integer orderId) {

		OrderDetails details = getOrderDetails(orderId);

		details.getItems().forEach(item -> {
			item.setOrderId(orderId);
			itemsRepository.delete(item);
		});

		orderRepository.delete(details.getOrder());
		return "order deleted successfully!";
	}
}
