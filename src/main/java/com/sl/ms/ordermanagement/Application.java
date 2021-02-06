package com.sl.ms.ordermanagement;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.sl.ms.ordermanagement.dao.ItemsRepository;
import com.sl.ms.ordermanagement.dao.OrderRepository;
import com.sl.ms.ordermanagement.sql.domain.Item;
import com.sl.ms.ordermanagement.sql.domain.Order;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		
	}
	
	@Bean
	CommandLineRunner commandLineRunner (OrderRepository OrderRepository, ItemsRepository itemsRepository) {
		return new CommandLineRunner() {
			
			@Override
			public void run(String... args) throws Exception {
				OrderRepository.save(new Order(1, "order_1", 200));
				itemsRepository.save(new Item(1, "parle-g", 1, 10, 10));
				itemsRepository.save(new Item(1, "marigold", 2, 10, 20));
				itemsRepository.save(new Item(1, "rusk", 3, 30, 30));
			}
		};
	}
}
