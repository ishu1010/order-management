package com.sl.ms.ordermanagement.controller;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sl.ms.ordermanagement.dto.OrderDetails;
import com.sl.ms.ordermanagement.service.OrderManagementService;
import com.sl.ms.ordermanagement.sql.domain.Item;
import com.sl.ms.ordermanagement.sql.domain.Order;

public class OrderManagementControllerTest {
	@Mock
	private OrderManagementService orderManagementService;

	@InjectMocks
	@Spy
	private OrderManagementController orderManagementController;
	
	private MockMvc mvc;
	private OrderDetails orderDetails;
	private Order order;
	private Item item;
	private List<Item> items ;
	private ObjectMapper mapper;
	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
		mvc = MockMvcBuilders.standaloneSetup(orderManagementController).build();
		mapper = new ObjectMapper();
		order = new Order(1, "order_1", 200);
		item = new Item(1, "item_1", 1, 2, 20);
		 items = new ArrayList<Item>();
		items.add(item);
		 orderDetails = new OrderDetails(order, items);
	}
	
	
	@Test
	public void getAllOrders_shouldReturnSuccessful() throws Exception {
		List<Order> orders = new ArrayList<Order>();
		orders.add(order);
		when(orderManagementService.getAllOrders()).thenReturn(orders);
		MvcResult result = mvc.perform(get("/orders").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().is2xxSuccessful()).andReturn();
		List<Order> actual = mapper.readValue(result.getResponse().getContentAsString(),
				new TypeReference<List<Order>>() {});
		Assertions.assertEquals(orders.toString(), actual.toString());
		verify(orderManagementService, times(1)).getAllOrders();
	}
	
	
	@Test
	public void getOrderById_shouldReturnSuccessful() throws Exception {
		when(orderManagementService.getOrderDetails(Mockito.anyInt())).thenReturn(orderDetails);
		MvcResult result = mvc.perform(get("/orders/{order_id}", 1).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().is2xxSuccessful()).andReturn();
		OrderDetails actual = mapper.readValue(result.getResponse().getContentAsString(), OrderDetails.class);
		Assertions.assertEquals(orderDetails.toString(), actual.toString());
		verify(orderManagementService, times(1)).getOrderDetails(Mockito.anyInt());
	}
	
	
	@Test
	public void saveOrderById_shouldReturnSuccessful() throws Exception {
		when(orderManagementService.saveOrderByOrderId(Mockito.anyInt(), Mockito.<OrderDetails>any())).thenReturn("order saved successfully!");
		MvcResult result = mvc.perform(post("/orders/{order_id}", 1).content(mapper.writeValueAsString(orderDetails)).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().is2xxSuccessful()).andReturn();
		String actual = result.getResponse().getContentAsString();
		Assertions.assertEquals("order saved successfully!", actual);
		verify(orderManagementService, times(1)).saveOrderByOrderId(Mockito.anyInt(), Mockito.<OrderDetails>any());
	}
	
	@Test
	public void deleteOrderById_shouldReturnSuccessful() throws Exception {
	when(orderManagementService.deleteOrderByOrderId(Mockito.anyInt())).thenReturn("order deleted successfully!");
		MvcResult result = mvc.perform(delete("/orders/{order_id}", 1).accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().is2xxSuccessful()).andReturn();
		Assertions.assertEquals("order deleted successfully!", result.getResponse().getContentAsString());
		verify(orderManagementService, times(1)).deleteOrderByOrderId(Mockito.anyInt());
	}
	
}
