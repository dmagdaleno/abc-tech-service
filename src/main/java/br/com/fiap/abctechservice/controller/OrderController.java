package br.com.fiap.abctechservice.controller;

import br.com.fiap.abctechservice.application.OrderApplication;
import br.com.fiap.abctechservice.application.dto.OrderDto;
import br.com.fiap.abctechservice.model.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderApplication application;

    public OrderController(OrderApplication orderApplication) {
        this.application = orderApplication;
    }

    @GetMapping
    public ResponseEntity<List<Order>> listOrderByOperatorId(@RequestParam Long operatorId) {
        return ResponseEntity.ok(application.listOrdersByOperator(operatorId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrder(@PathVariable Long id) {
        return ResponseEntity.ok(application.findOrderBy(id));
    }

    @PostMapping
    public ResponseEntity<URI> createOrder(@Valid @RequestBody OrderDto orderDto){
        Order saved = application.createOrder(orderDto);
        return ResponseEntity.created(buildUri(saved)).build();
    }

    private URI buildUri(Order order) {
        return ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(order.getId())
            .toUri();
    }
}