package br.com.harley.notification.service;

import org.springframework.stereotype.Service;

import br.com.harley.notification.entity.Order;
import br.com.harley.notification.repository.OrderRepository;

@Service
public class OrderService {

	OrderRepository orderRepository;
	
	public OrderService(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}

	public Order addOrder(Order order) {
		return orderRepository.save(order);
	}
}
