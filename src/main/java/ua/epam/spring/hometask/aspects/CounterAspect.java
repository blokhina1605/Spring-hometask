package ua.epam.spring.hometask.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import ua.epam.spring.hometask.domain.Event;
import ua.epam.spring.hometask.domain.Ticket;

import java.util.*;

/**
 * @author Yevheniia_Blokhina.
 */
@Aspect
@Component
public class CounterAspect {

    private Map<Event, Integer> nameCounter = new HashMap<>();

    private Map<String, Integer> priceCounter = new HashMap<>();

    private Map<Ticket, Integer> bookTicketsCounter = new HashMap<>();
    public int cointer = 1;

    @Pointcut("execution(* ua.epam.spring.hometask.service.impl.EventServiceImpl.findByName(..))")
    public void anyEventGetByName() {
    }

    @Pointcut("execution(* ua.epam.spring.hometask.service.impl.EventServiceImpl.getPriceByName(..))")
    public void anyEventGetPrices() {
    }

    @Pointcut("execution(* ua.epam.spring.hometask.service.impl.BookingServiceImpl.bookTickets(..))")
    public void anyEventBookTickets() {
    }

    @AfterReturning(pointcut = "anyEventGetByName()", returning = "event")
    public void countGetNames(Event event) {
        if (!nameCounter.containsKey(event))
            nameCounter.put(event, 0);
        nameCounter.put(event, nameCounter.get(event) + 1);
    }

    @AfterReturning(pointcut = "anyEventGetPrices()", returning = "ticketPrice")
    public void countGetPrices(JoinPoint joinPoint) {
        String eventName = Arrays.toString(joinPoint.getArgs());
        if (!priceCounter.containsKey(eventName))
            priceCounter.put(eventName, 0);
        priceCounter.put(eventName, priceCounter.get(eventName) + 1);
    }

    @AfterReturning(pointcut = "anyEventBookTickets()")
    public void countBookTickets(JoinPoint joinPoint) {
        Set ticketSet = new HashSet<>(Arrays.asList(joinPoint.getArgs()));
        for (Object ticket : ticketSet) {
            Ticket ticket1 = (Ticket) ticket;
            if (!bookTicketsCounter.containsKey(ticket1))
                bookTicketsCounter.put(ticket1, 0);
            bookTicketsCounter.put(ticket1, bookTicketsCounter.get(ticket) + 1);
        }
    }

    public Map<Event, Integer> getNameCounter() {
        return nameCounter;
    }

    public Map<String, Integer> getPriceCounter() {
        return priceCounter;
    }

    public Map<Ticket, Integer> getBookTicketsCounter() {
        return bookTicketsCounter;
    }

    public int getCointer() {
        return cointer;
    }
}