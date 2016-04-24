package ua.epam.spring.hometask.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import ua.epam.spring.hometask.domain.Event;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Yevheniia_Blokhina.
 */
@Aspect
@Component
public class CounterAspect {

    private Map<String, Integer> nameCounter = new HashMap<>();

    private Map<Double, Integer> priceCounter = new HashMap<>();

    private Map<Event, Integer> bookTicketsCounter = new HashMap<>();
    public int cointer = 1;

    @Pointcut("execution(* ua.epam.spring.hometask.daos.impl.EventDaoImpl.findByName(..))")
    public void anyEventGetByName() {
    }

    @Pointcut("execution(* ua.epam.spring.hometask.domain.Event.getBasePrice(..))")
    public void anyEventGetPrices() {
    }

    @Pointcut("execution(* ua.epam.spring.hometask.service.impl.BookingServiceImpl.bookTickets(..))")
    public void anyEventBookTickets() {
    }

    @Pointcut("execution(* ua.epam.spring.hometask.daos.impl.EventDaoImpl.findByName(..))")
    public void doBeforeTask() {
    }

    @Before("doBeforeTask()")
    public void Lol() {
        System.out.println("LOOOOOOL");

        cointer++;
    }

    @Before("execution(* ua.epam.spring.hometask.daos.impl.EventDaoImpl.findByName(..))")
    public void lol(){
        System.out.println("tuuuut loooooooooooooooooool");
    }

    @AfterReturning(pointcut = "doBeforeTask()", returning = "eventName")
    public void lol2(String eventName){
        System.out.println("tuuuut looooooooooooooooooo222222222222222222222222222l");

    }
    @AfterReturning(pointcut = "execution(* ua.epam.spring.hometask.daos.impl.EventDaoImpl.findByName(..))", returning = "eventName")
    public void countGetNames(JoinPoint joinPoint, String eventName) {
        System.out.println("==================================================");
        if (!nameCounter.containsKey(eventName))
            nameCounter.put(eventName, 0);
        nameCounter.put(eventName, nameCounter.get(eventName) + 1);
        System.out.println("==================================================");

    }

    @AfterReturning(pointcut = "anyEventGetPrices()", returning = "ticketPrice")
    public void countGetPrices(JoinPoint joinPoint, double ticketPrice) {
        System.out.println("**********************************************************");

        if (!priceCounter.containsKey(ticketPrice))
            priceCounter.put(ticketPrice, 0);
        priceCounter.put(ticketPrice, priceCounter.get(ticketPrice) + 1);
    }

    @AfterReturning(pointcut = "anyEventBookTickets()")
    public void countBookTickets(JoinPoint joinPoint) {
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++");

        Object targetObject = joinPoint.getTarget();
        if (!bookTicketsCounter.containsKey(targetObject))
            bookTicketsCounter.put((Event) targetObject, 0);
        bookTicketsCounter.put((Event) targetObject, bookTicketsCounter.get(targetObject) + 1);
    }

    public Map<String, Integer> getNameCounter() {
        return nameCounter;
    }

    public Map<Double, Integer> getPriceCounter() {
        return priceCounter;
    }

    public Map<Event, Integer> getBookTicketsCounter() {
        return bookTicketsCounter;
    }

    public int getCointer() {
        return cointer;
    }
}