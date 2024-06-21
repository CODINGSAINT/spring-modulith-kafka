package dev.pallav.service.orders;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
 class OrdersController {

    private final OrderService orderService;

    OrdersController(OrderService orderService) {
        this.orderService = orderService;
    }


    @PostMapping("orders")
    Order place(@RequestBody Order order){
        var orderPlaced=  orderService.place(order);

        return orderPlaced;
    }


}

@Service
@Transactional
class OrderService{

    private final OrdersRepository ordersRepository;

    private final ApplicationEventPublisher publisher;
    OrderService(OrdersRepository ordersRepository, ApplicationEventPublisher publisher) {

        this.ordersRepository = ordersRepository;
        this.publisher = publisher;
    }
    Order place(Order order){
        var orderPlaced= ordersRepository.save(order);
        publisher.publishEvent(new OrderPlacedEvent(orderPlaced.id()));
        return orderPlaced;
    }
}
interface OrdersRepository extends CrudRepository<Order, Integer>{}
@Table("orders")
record Order(@Id Integer id, Set<LineItem> lineItems){}
@Table("order_line_items")
record  LineItem(@Id Integer id, int product, int quantity){}