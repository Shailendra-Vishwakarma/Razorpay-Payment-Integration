package com.paymentapp.Repo;

import com.paymentapp.Entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepo extends JpaRepository<Order,Integer> {
      public Order findByrazorpayOrderId(String razorpayOrderId);
}
