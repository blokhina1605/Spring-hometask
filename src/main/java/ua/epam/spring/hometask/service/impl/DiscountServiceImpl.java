package ua.epam.spring.hometask.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.epam.spring.hometask.domain.strategy.BirthdayDiscount;
import ua.epam.spring.hometask.domain.User;
import ua.epam.spring.hometask.domain.strategy.DiscountStrategy;
import ua.epam.spring.hometask.service.DiscountService;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Yevheniia_Blokhina.
 */
@Service
public class DiscountServiceImpl implements DiscountService {

    private List<DiscountStrategy> discountStrategies;

    @Override
    public byte getDiscount(@Nullable User user,
                            @Nonnull LocalDateTime airDateTime,
                            long numberOfTickets) {
        byte finalDiscount = 0;
        for (DiscountStrategy birthdayDiscount : discountStrategies) {
            byte currentDiscount = birthdayDiscount.getDiscount(user, airDateTime, numberOfTickets);
            if (currentDiscount < finalDiscount)
                finalDiscount = currentDiscount;
        }
        return finalDiscount;
    }

    public List<DiscountStrategy> getDiscountStrategies() {
        return discountStrategies;
    }

    @Autowired
    public void setDiscountStrategies(List<DiscountStrategy> discountStrategies) {
        this.discountStrategies = discountStrategies;
    }
}
