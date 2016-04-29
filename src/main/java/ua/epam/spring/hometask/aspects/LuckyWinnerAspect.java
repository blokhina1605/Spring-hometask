package ua.epam.spring.hometask.aspects;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import ua.epam.spring.hometask.domain.Event;
import ua.epam.spring.hometask.domain.Ticket;
import ua.epam.spring.hometask.domain.User;

import java.util.Random;
import java.util.Set;

/**
 * @author Yevheniia_Blokhina.
 */

@Aspect
@Component
public class LuckyWinnerAspect {

    @Before("execution(public void bookTickets(..)) && args(tickets) && within(ua.epam.spring.hometask.service.impl.BookingServiceImpl))")
    private void allTicketBook(Set<Ticket> tickets) {
        if (getLucky()) {
            for (Ticket ticket : tickets) {
                Event event = ticket.getEvent();
                event.setBasePrice(0);
                ticket.getUser().setLuckyEvent(event);
            }
        }
    }

    private boolean getLucky() {
        Random random = new Random();
        return random.nextBoolean();
    }
}