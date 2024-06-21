package dev.pallav.service.orders;

import org.springframework.modulith.events.Externalized;

@Externalized("service.order")
public record OrderPlacedEvent(int id ) {
}
