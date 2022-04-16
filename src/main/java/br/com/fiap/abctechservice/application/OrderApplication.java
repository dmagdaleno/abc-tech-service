package br.com.fiap.abctechservice.application;

import br.com.fiap.abctechservice.application.dto.OrderDto;
import br.com.fiap.abctechservice.model.Order;

import java.util.List;

public interface OrderApplication {
    Order createOrder(OrderDto orderDto);
    List<Order> listOrdersByOperator(Long operatorId);
    Order findOrderBy(Long id);
}