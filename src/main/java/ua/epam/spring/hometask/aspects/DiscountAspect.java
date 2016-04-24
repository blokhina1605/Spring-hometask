package ua.epam.spring.hometask.aspects;

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

    private Map<Double, Integer> totalDiscountCounter = new HashMap<>();

    private Map<Double, Map<String, Integer>> userDiscountCounter = new HashMap<>();

    @Pointcut("execution(private double getBirthdayDiscount(..))")
    private void allBirthdayDiscountCalls() {
    }

    @Pointcut("execution(private double getEveryTenDiscount(..))")
    private void everyTenDiscountCalls() {
    }

    @AfterReturning(pointcut = "allBirthdayDiscountCalls() || everyTenDiscountCalls()", returning = "discount")
    private void countAllBirthDayDiscountCalls(Double discount) {
        if (!totalDiscountCounter.containsKey(discount))
            totalDiscountCounter.put(discount, 0);
        totalDiscountCounter.put(discount, totalDiscountCounter.get(discount) + 1);
    }

    @AfterReturning(pointcut = "execution(public double getDiscount(..)) && args(user,..)", returning = "discount")
    private void discountForEveryUserCalls(User user, Double discount) {
        String userName = user.getFirstName()   ;
        if (!userDiscountCounter.containsKey(discount)) {
            userDiscountCounter.put(discount, new HashMap<String, Integer>());
            userDiscountCounter.get(discount).put(userName, 0);
        }
        if (!userDiscountCounter.get(discount).containsKey(userName)) {
            userDiscountCounter.get(discount).put(userName, 0);
        }
        userDiscountCounter.get(discount).put(userName, userDiscountCounter.get(discount).get(userName) + 1);
    }

    public Map<Double, Integer> getTotalDiscountCounter() {
        return totalDiscountCounter;
    }

    public Map<Double, Map<String, Integer>> getUserDiscountCounter() {
        return userDiscountCounter;
    }
}