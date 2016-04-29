package ua.epam.spring.hometask.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import ua.epam.spring.hometask.domain.User;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Yevheniia_Blokhina.
 */
@Aspect
@Component
public class DiscountAspect {

    private Map<String, Integer> totalDiscountCounter = new HashMap<>();

    private Map<String, Map<String, Integer>> userDiscountCounter = new HashMap<>();

    @Pointcut("execution(* ua.epam.spring.hometask.domain.strategy.BirthdayDiscount.getDiscount(..))")
    private void allBirthdayDiscountCalls() {
    }

    @Pointcut("execution(* ua.epam.spring.hometask.domain.strategy.TicketDiscount.getDiscount(..))")
    private void everyTenDiscountCalls() {
    }

    @AfterReturning(pointcut = "allBirthdayDiscountCalls() || everyTenDiscountCalls()")
    private void countAllBirthDayDiscountCalls(JoinPoint joinPoint) {
        String discountName = joinPoint.getTarget().getClass().getCanonicalName();
        if (!totalDiscountCounter.containsKey(discountName))
            totalDiscountCounter.put(discountName, 0);
        totalDiscountCounter.put(discountName, totalDiscountCounter.get(discountName) + 1);
    }

    @AfterReturning(pointcut = "execution(* ua.epam.spring.hometask.domain.strategy.DiscountStrategy.getDiscount(..)) && args(user,..)")
    private void discountForEveryUserCalls(JoinPoint joinPoint, User user) {
        String userName = user.getFirstName();
        String discountName = joinPoint.getTarget().getClass().getCanonicalName();

        if (!userDiscountCounter.containsKey(discountName)) {
            userDiscountCounter.put(discountName, new HashMap<String, Integer>());
            userDiscountCounter.get(discountName).put(userName, 0);
        }
        if (!userDiscountCounter.get(discountName).containsKey(userName)) {
            userDiscountCounter.get(discountName).put(userName, 0);
        }
        userDiscountCounter.get(discountName).put(userName, userDiscountCounter.get(discountName).get(userName) + 1);
    }

    public Map<String, Integer> getTotalDiscountCounter() {
        return totalDiscountCounter;
    }

    public Map<String, Map<String, Integer>> getUserDiscountCounter() {
        return userDiscountCounter;
    }
}