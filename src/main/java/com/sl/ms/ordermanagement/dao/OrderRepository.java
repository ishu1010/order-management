package com.sl.ms.ordermanagement.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sl.ms.ordermanagement.sql.domain.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer>{
	}
