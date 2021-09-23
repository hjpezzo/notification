package br.com.harley.notification.amqp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import br.com.harley.notification.entity.BarItem;
import br.com.harley.notification.entity.KitchenItem;
import br.com.harley.notification.entity.Order;
import br.com.harley.notification.service.OrderService;

@Component
public class OrderConsumer {

	OrderService orderService;
	RabbitTemplate rabbitTemplate;
	JavaMailSender javaMailSender;

	public OrderConsumer(OrderService orderService, RabbitTemplate rabbitTemplate, JavaMailSender javaMailSender) {
		this.orderService = orderService;
		this.rabbitTemplate = rabbitTemplate;
		this.javaMailSender = javaMailSender;
	}

	@RabbitListener(queues = RabbitConfig.QUEUE_TO_NOTIFICATION)
	public void consumer(@Payload Order order) {
		Logger logger = LoggerFactory.getLogger(OrderConsumer.class);
		logger.info("Recebido Notificação do pedido: ".concat(order.getId()));
		orderService.addOrder(order);

		StringBuilder content = new StringBuilder();
		content.append("Garçom: ".concat(order.getWaiter()).concat("\n\r"));
		content.append("Mesa: ".concat(order.getTableNumber().toString()).concat("\n\r"));

		if (order.getBarItens() != null) {
			content.append("Bebidas: \n");
			for (BarItem barItem : order.getBarItens()) {
				content.append(barItem.getQuantity().toString().concat(" ").concat(barItem.getName()).concat("\n"));
				content.append(barItem.getNote().concat("\n\r"));
			}
		}

		if (order.getKitchenItens() != null) {
			content.append("Pratos: \n");
			for (KitchenItem kitchenItem : order.getKitchenItens()) {
				content.append(
						kitchenItem.getQuantity().toString().concat(" ").concat(kitchenItem.getName()).concat("\n"));
				content.append(kitchenItem.getNote().concat("\n\r"));
			}
		}

		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo("hjpezzo@gmail.com");
		message.setText(content.toString());
		message.setSubject("Pedido finalizado");
		message.setFrom("hjpezzo@gmail.com");
		javaMailSender.send(message);
	}

}
