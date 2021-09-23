package br.com.harley.notification.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.harley.notification.entity.Order;

public interface OrderRepository extends MongoRepository<Order, String> {

}
