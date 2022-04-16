package br.com.fiap.abctechservice.service.impl;

import br.com.fiap.abctechservice.handler.exception.MaxAssistsException;
import br.com.fiap.abctechservice.handler.exception.MinimumAssistsRequiredException;
import br.com.fiap.abctechservice.handler.exception.OrderNotFoundException;
import br.com.fiap.abctechservice.model.Assistance;
import br.com.fiap.abctechservice.model.Order;
import br.com.fiap.abctechservice.repository.AssistanceRepository;
import br.com.fiap.abctechservice.repository.OrderRepository;
import br.com.fiap.abctechservice.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final AssistanceRepository assistanceRepository;

    public OrderServiceImpl (
        OrderRepository orderRepository,
        AssistanceRepository assistanceRepository
    ) {
        this.orderRepository = orderRepository;
        this.assistanceRepository = assistanceRepository;
    }

    @Override
    public Order saveOrder(Order order, List<Long> arrayAssists)  {
        ArrayList<Assistance> assistances = new ArrayList<>();

        arrayAssists.forEach( i -> {
            Assistance assistance = this.assistanceRepository.findById(i).orElseThrow();
            assistances.add(assistance);
        });

        order.setServices(assistances);

        if (!order.hasMinAssists()){
            throw new MinimumAssistsRequiredException("Array de assistências inváldio", "Por favor, envie ao menos 1 assistência" );
        } else if (order.exceedsMaxAssists()){
            throw new MaxAssistsException("Array de assistências inváldio", "O número máximo de assistências é 15");
        }

        return orderRepository.save(order);
    }

    @Override
    public List<Order> listOrdersByOperator(Long operatorId) {
        return orderRepository.findAllOrderByOperatorId(operatorId);
    }

    @Override
    public Order findOrderBy(Long id) {
        return orderRepository
            .findById(id)
            .orElseThrow(() -> new OrderNotFoundException(
                "Ordem não encontrada",
                String.format("O identificador [%d] passado na requisição não existe", id)
            ));
    }
}