package ua.epam.spring.hometask.domain.strategy;

import org.springframework.stereotype.Component;
import ua.epam.spring.hometask.domain.User;

import java.time.LocalDateTime;

/**
 * @author Yevheniia_Blokhina.
 */

@Component
public class TicketDiscount implements DiscountStrategy {

    @Override
    public byte getDiscount(User user, LocalDateTime date, long numberOfTickets) {
        return isTicketDiscount(user, numberOfTickets) ? (byte) 50 : 0;
    }

    private boolean isTicketDiscount(User user, long numberOfTickets) {
        return (user.getTickets().size()) % numberOfTickets == 0;
    }
}
