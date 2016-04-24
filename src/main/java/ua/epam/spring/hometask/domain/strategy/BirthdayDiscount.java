package ua.epam.spring.hometask.domain.strategy;

import org.springframework.stereotype.Component;
import ua.epam.spring.hometask.domain.User;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author Yevheniia_Blokhina.
 */

@Component
public class BirthdayDiscount implements DiscountStrategy {

    @Override
    public byte getDiscount(User user, LocalDateTime date, long numberOfTickets) {
        if (isBirthdayDiscount(user, LocalDate.from(date)))
            return 5;
        else return 0;
    }

    private boolean isBirthdayDiscount(User user, LocalDate date) {
        return (user.getBirthDate().isAfter(date.minusDays(2))
                && user.getBirthDate().isBefore(date.plusDays(2)));
    }
}


