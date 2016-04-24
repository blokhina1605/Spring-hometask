package ua.epam.spring.hometask.aspects;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import ua.epam.spring.hometask.domain.Ticket;
import ua.epam.spring.hometask.domain.User;

/**
 * @author Yevheniia_Blokhina.
 */

@Aspect
@Component
public class LuckyWinnerAspect {

    @Before("execution(public void bookTicket(..)) && args(user, ticket) && within(ua.epam.spring.hometask.service.impl.BookingServiceImpl))")
    private void allTicketBook(User user, Ticket ticket) {
//        user.setLucky(false);
//        String userName = user.getFirstName()   ;
//        if (userName.contains("Q")) {
//            ticket.setPrice(0);
//            user.setLucky(true);
//        }
    }
}