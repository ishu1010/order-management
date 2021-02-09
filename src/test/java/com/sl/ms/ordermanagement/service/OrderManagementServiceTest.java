package com.sl.ms.ordermanagement.service;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import com.sl.ms.ordermanagement.dao.ItemsRepository;
import com.sl.ms.ordermanagement.dao.OrderRepository;
import com.sl.ms.ordermanagement.dto.OrderDetails;
import com.sl.ms.ordermanagement.sql.domain.Item;
import com.sl.ms.ordermanagement.sql.domain.Order;

public class OrderManagementServiceTest {
	@Mock
	private OrderRepository orderRepository;
	@Mock
	private ItemsRepository itemsRepository;
	
	@InjectMocks
	@Spy
	private OrderManagementService orderManagementService;
	private OrderDetails orderDetails;
	private Order order;
	private Item item;
	private List<Item> items ;
	
	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
		order = new Order(1, "order_1", 200);
		item = new Item(1, "item_1", 1, 2, 20);
		 items = new ArrayList<Item>();
		items.add(item);
		 orderDetails = new OrderDetails(order, items);
	}
	
	@Test
	public void getAllorder() {
		List<Order> orders = new ArrayList<Order>();
		orders.add(order);
		when(orderRepository.findAll()).thenReturn(orders);
		orderManagementService.getAllOrders();
		verify(orderManagementService, times(1)).getAllOrders();
	}
	
	@Test
	public void getOrderDetails() {
		when(orderRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(order));
		when(itemsRepository.findByOrderId(Mockito.anyInt())).thenReturn(items);
		orderManagementService.getOrderDetails(1);
		verify(orderManagementService, times(1)).getOrderDetails(1);
	}
	
	@Test
	public void saveOrderByOrderId() {
		when(orderRepository.save(Mockito.<Order>any())).thenReturn(order);
		when(itemsRepository.save(Mockito.<Item>any())).thenReturn(item);
		orderManagementService.saveOrderByOrderId(1, orderDetails);
		verify(orderManagementService, times(1)).saveOrderByOrderId(1,orderDetails);
	}
	
	@Test
	public void deleteOrderByOrderId() {
		when(orderRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(order));
		when(itemsRepository.findByOrderId(Mockito.anyInt())).thenReturn(items);
		doNothing().when(orderRepository).delete(Mockito.<Order>any());
		doNothing().when(itemsRepository).delete(Mockito.<Item>any());
		orderManagementService.deleteOrderByOrderId(1);
		verify(orderManagementService, times(1)).getOrderDetails(1);
	}
}
