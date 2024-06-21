package dev.pallav.service.products;

import dev.pallav.service.orders.OrderPlacedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.modulith.events.ApplicationModuleListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionalEventListener;


@Service
 class Product {


    private static Logger logger = LoggerFactory.getLogger(Product.class);


    @ApplicationModuleListener
void updateProductOnOrderPlacedEvent(OrderPlacedEvent event) throws InterruptedException {
        logger.info("Starting to listen OrderPlacedEvent [ "+event.id()+" ]");
     Thread.sleep(15000);
        logger.info("Stopping to listen OrderPlacedEvent [ "+event.id()+" ]");


    }
}
