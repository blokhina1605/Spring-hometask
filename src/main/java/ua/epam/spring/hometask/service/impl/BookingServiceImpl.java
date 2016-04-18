package ua.epam.spring.hometask.service.impl;

import ua.epam.spring.hometask.daos.AuditoriumDao;
import ua.epam.spring.hometask.daos.TicketDao;
import ua.epam.spring.hometask.daos.UserDao;
import ua.epam.spring.hometask.domain.*;
import ua.epam.spring.hometask.service.BookingService;
import ua.epam.spring.hometask.service.DiscountService;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.time.LocalDateTime;
import java.util.*;

/**
 * @author Yevheniia_Blokhina.
 */
public class BookingServiceImpl implements BookingService {

    private TicketDao ticketDAO;

    private UserDao userDao;

    private DiscountService discountService;

    @Override
    public double getTicketsPrice(@Nonnull Event event,
                                  @Nonnull LocalDateTime dateTime,
                                  @Nullable User user,
                                  @Nonnull Set<Long> seats) {

        double price = getRatingPrice(event);
        price = getSeatsPrice(event, dateTime, seats, price);
        byte discount = discountService.getDiscount(user, dateTime, seats.size());
        return price * discount / 100;
    }

    private double getSeatsPrice(@Nonnull Event event, @Nonnull LocalDateTime dateTime, @Nonnull Set<Long> seats, double price) {
        Map<LocalDateTime, Auditorium> auditoriumMap = event.getAuditoriums();
        for (Map.Entry<LocalDateTime, Auditorium> timeAuditEntry : auditoriumMap.entrySet()) {
            if (timeAuditEntry.getKey().equals(dateTime)) {
                for (Long seat : seats) {
                    if (timeAuditEntry.getValue().getVipSeats().contains(seat)) {
                        price += price * 1.5;
                    } else price += price;
                }
            }
        }
        return price;
    }

    private double getRatingPrice(Event event) {
        EventRating rating = event.getRating();
        double price = event.getBasePrice();
        if (rating == EventRating.HIGH) {
            price = price * 1.2;
        } else if (rating == EventRating.LOW) {
            price = price * 0.8;
        }
        return price;
    }

    @Override
    public void bookTickets(@Nonnull Set<Ticket> tickets) {
        for (Ticket ticket : tickets) {
            User user = ticket.getUser();
            if (userDao.getById(user.getId()) != null) {
                user.putTicket(ticket);
                userDao.update(user);
            }
            ticketDAO.create(ticket);
        }

    }

    @Nonnull
    @Override
    public Set<Ticket> getPurchasedTicketsForEvent(@Nonnull Event event, @Nonnull LocalDateTime dateTime) {
        Set<Ticket> tickets = new HashSet<>();

        for (Ticket ticket : ticketDAO.getAll()) {
            if (ticket.getEvent().equals(event) && ticket.getDateTime().equals(dateTime))
                tickets.add(ticket);
        }
        return tickets;
    }

    public TicketDao getTicketDAO() {
        return ticketDAO;
    }

    public void setTicketDAO(TicketDao ticketDAO) {
        this.ticketDAO = ticketDAO;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public DiscountService getDiscountService() {
        return discountService;
    }

    public void setDiscountService(DiscountService discountService) {
        this.discountService = discountService;
    }
}
