package ua.epam.spring.hometask.domain.strategy;

import ua.epam.spring.hometask.domain.User;

import java.time.LocalDateTime;

/**
 * @author Yevheniia_Blokhina.
 */
public interface DiscountStrategy {
    byte getDiscount(User user, LocalDateTime date, long numberOfTickets);
}
