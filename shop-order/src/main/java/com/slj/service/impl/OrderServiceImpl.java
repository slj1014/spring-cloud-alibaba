package com.slj.service.impl;

import com.slj.dao.OrderDao;
import com.slj.domain.Order;
import com.slj.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDao orderDao;

    @Override
    public Order createOrder(Order order) {
        return orderDao.save(order);
    }
}
