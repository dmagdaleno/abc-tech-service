package br.com.fiap.abctechservice.application.impl;

import br.com.fiap.abctechservice.application.OrderApplication;
import br.com.fiap.abctechservice.application.dto.OrderDto;
import br.com.fiap.abctechservice.application.dto.OrderLocationDto;
import br.com.fiap.abctechservice.model.Order;
import br.com.fiap.abctechservice.model.OrderLocation;
import br.com.fiap.abctechservice.service.OrderService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderApplicationImpl implements OrderApplication {

    private final OrderService service;

    public OrderApplicationImpl(OrderService service) {
        this.service = service;
    }

    @Override
    public Order createOrder(OrderDto orderDto) {
        Order order = new Order();
        order.setOperatorId(orderDto.getOperatorId());
        order.setStartOrderLocation(getOrderLocationFromOrderLocationDto(orderDto.getStart()));
        order.setEndOrderLocation(getOrderLocationFromOrderLocationDto(orderDto.getEnd()));
        return service.saveOrder(order, orderDto.getServices());
    }

    private OrderLocation getOrderLocationFromOrderLocationDto(OrderLocationDto orderLocationDto) {
        OrderLocation location = new OrderLocation();
        location.setLatitude(orderLocationDto.getLatitude());
        location.setLongitude(orderLocationDto.getLatitude());
        location.setDate(orderLocationDto.getDateTime());
        return location;
    }

    @Override
    public List<Order> listOrdersByOperator(Long operatorId) {
        return service.listOrdersByOperator(operatorId);
    }

    @Override
    public Order findOrderBy(Long id) {
        return service.findOrderBy(id);
    }
}