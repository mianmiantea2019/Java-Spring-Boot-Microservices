package com.cast.order.service;

import com.cast.demo.clients.UserClient;
import com.cast.demo.pojo.User;
import com.cast.order.mapper.OrderMapper;
import com.cast.order.pojo.Order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private UserClient userClient;

    public Order queryOrderById(Long orderId) {
        Order order = orderMapper.findById(orderId);
        User user = userClient.findById(order.getUserId());
        order.setUser(user);
        return order;
    }
}
  
