package com.sl.ms.ordermanagement.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sl.ms.ordermanagement.sql.domain.Item;

@Repository
public interface ItemsRepository extends CrudRepository<Item, Integer>{
	
	@Query(value = "select * from SL_ITEMS where order_id=:orderId", nativeQuery = true)
	public List<Item> findByOrderId( @Param("orderId") Integer orderId);
}

