package com.sl.ms.ordermanagement.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sl.ms.ordermanagement.sql.domain.Order;

@Repository
public interface OrderRepository extends CrudRepository<Order, Integer> {
	public List<Order> findAll();
}
