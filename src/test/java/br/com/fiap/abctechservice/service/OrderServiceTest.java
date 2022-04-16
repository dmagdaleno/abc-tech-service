package br.com.fiap.abctechservice.service;

import br.com.fiap.abctechservice.handler.exception.MaxAssistsException;
import br.com.fiap.abctechservice.handler.exception.MinimumAssistsRequiredException;
import br.com.fiap.abctechservice.model.Assistance;
import br.com.fiap.abctechservice.model.Order;
import br.com.fiap.abctechservice.repository.AssistanceRepository;
import br.com.fiap.abctechservice.repository.OrderRepository;
import br.com.fiap.abctechservice.service.impl.OrderServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;
    @Mock
    private AssistanceRepository assistanceRepository;

    private OrderService service;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        service = new OrderServiceImpl(orderRepository, assistanceRepository);
        when(assistanceRepository.findById(any()))
            .thenReturn(Optional.of(new Assistance(1L, "Teste", "Teste Description")));
    }

    @Test
    public void serviceShouldNotBeNull(){
        Assertions.assertNotNull(service);
    }

    @Test
    public void shouldSuccessfullyCreateOrder() throws Exception {
        Order newOrder = new Order();
        newOrder.setOperatorId(1234L);

        service.saveOrder(newOrder, generateMockAssistance(1));

        verify(orderRepository, times(1)).save(newOrder);
    }

    @Test
    public void shouldThrowExceptionWhenNoAssistanceIsSelected() throws Exception {
        Order newOrder = new Order();
        newOrder.setOperatorId(1234L);

        Assertions.assertThrows(MinimumAssistsRequiredException.class, () -> service.saveOrder(newOrder, List.of()));
        verify(orderRepository, times(0)).save(newOrder);
    }

    @Test
    public void shouldThrowExceptionWhenAssistanceNumberIsOverTheLimit() throws Exception {
        Order newOrder = new Order();
        newOrder.setOperatorId(1234L);

        Assertions.assertThrows(MaxAssistsException.class, () -> service.saveOrder(newOrder, generateMockAssistance(20)));
        verify(orderRepository, times(0)).save(newOrder);
    }

    private List<Long> generateMockAssistance(int number) {
        ArrayList<Long> arrayList = new ArrayList<>();
        for (int x = 0; x < number; x++){
            arrayList.add(Integer.toUnsignedLong(x));
        }
        return  arrayList;
    }
}