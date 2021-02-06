package com.sl.ms.ordermanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sl.ms.ordermanagement.dto.OrderDetails;
import com.sl.ms.ordermanagement.service.OrderManagementService;
import com.sl.ms.ordermanagement.sql.domain.Order;

@RestController
public class OrderManagementController {

	@Autowired
	private OrderManagementService orderManagementService;
	
	@GetMapping("/orders")
	public ResponseEntity<List<Order>> getAllOrders()
	{
		List<Order> orders= orderManagementService.getAllOrders();
		return new ResponseEntity<>(orders, HttpStatus.OK);
	}
	
	@GetMapping("/orders/{order_id}")
	public ResponseEntity<OrderDetails> getOrderById(@PathVariable(name = "order_id") Integer orderId)
	{
		 OrderDetails orderDetails = orderManagementService.getOrderDetails(orderId);
		return new ResponseEntity<>(orderDetails, HttpStatus.OK);
	}
	@PostMapping(value = "/orders/{order_id}", consumes = "application/json")
	public ResponseEntity<String> saveOrder(@PathVariable(name = "order_id") Integer orderId, @RequestBody OrderDetails orderDetails) {
		 String sucessMsg = orderManagementService.saveOrderByOrderId(orderId, orderDetails);
		return new ResponseEntity<>(sucessMsg, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/orders/{order_id}")
	public ResponseEntity<String> updateProduct(@PathVariable(name = "order_id") Integer orderId) {
		String sucessMsg = orderManagementService.deleteOrderByOrderId(orderId);
		return new ResponseEntity<>(sucessMsg, HttpStatus.ACCEPTED);
	}
}
