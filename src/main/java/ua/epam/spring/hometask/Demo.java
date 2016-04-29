package ua.epam.spring.hometask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.epam.spring.hometask.aspects.CounterAspect;
import ua.epam.spring.hometask.domain.*;
import ua.epam.spring.hometask.service.*;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;


/**
 * @author Yevheniia_Blokhina.
 */

@Component
public class Demo {

    @Autowired
    private CounterAspect counterAspect;

    private enum UserRole {
        ADMIN, USER
    }

    @Autowired
    private BookingService bookingService;

    @Autowired
    private AuditoriumService auditoriumService;

    @Autowired
    private EventService eventService;

    @Autowired
    private UserService userService;

    @Autowired
    private DiscountService discountService;


    //region CONSOLE_MESSAGES
    private void showGreetings() {
        System.out.print("Hi! Please, enter your role to start work. \n" +
                "  - admin \n" +
                "  - user \n" +
                "Role: ");
    }

    private void showWrongMessage() {
        System.out.print("You entered wrong role. \n" +
                "Please, enter your role to start work. \n" +
                "  - admin \n" +
                "  - user \n" +
                "Role: ");
    }

    private void showUserCommands() {
        System.out.print("Available commands for user: \n" +
                " 1 - book ticket \n" +
                " 2 - get event by name \n" +
                " 3 - get all events \n" +
                " 4 - register user \n" +
                " 5 - get event price \n" +
                " 0 - exit \n" +
                "Command: ");
    }

    private void showAdminCommands() {
        System.out.print("Available commands for admin: \n" +
                " 1 - create event \n" +
                " 2 - book ticket \n" +
                " 3 - get event by name \n" +
                " 4 - get all events \n" +
                " 5 - register user \n" +
                " 6 - get user by email \n" +
                " 7 - get auditoriums \n" +
                " 8 - remove user \n" +
                " 9 - remove event \n" +
                " 10 - get all users \n" +
                " 11 - counter statistics \n" +
                " 12 - get event price \n" +
                " 0 - exit \n" +
                "Command: ");
    }
    //endregion

    private static String readFromConsole() {
        Scanner scanner = new Scanner(System.in);
        String text = null;
        if (scanner.hasNextLine()) {
            text = scanner.nextLine();
        }
        return text;
    }

    public void run() {
        populateTestData();
        showGreetings();
        while (true) {
            String userRole = readFromConsole();
            if (userRole.equalsIgnoreCase(UserRole.ADMIN.toString())) {
                getAdminActions();
                break;
            } else if (userRole.equalsIgnoreCase(UserRole.USER.toString())) {
                getUserActions();
                break;
            } else {
                showWrongMessage();
            }
        }
    }

    private void getAdminActions() {
        showAdminCommands();
        String command = readFromConsole();
        switch (command) {
            case "1":
                registerEvent();
                getAdminActions();
                break;
            case "2":
                bookTicket();
                getAdminActions();
                break;
            case "3":
                getEventByName();
                getAdminActions();
                break;
            case "4":
                getAllEvents();
                getAdminActions();
                break;
            case "5":
                registerUser();
                getAdminActions();
                break;
            case "6":
                getUserByEmail();
                getAdminActions();
                break;
            case "7":
                getAuditoriums();
                getAdminActions();
                break;
            case "8":
                removeUser();
                getAdminActions();
                break;
            case "9":
                removeEvent();
                getAdminActions();
                break;
            case "10":
                getAllUsers();
                getAdminActions();
                break;
            case "11":
                getCounterAspect();
                getAdminActions();
                break;
            case "12":
                getEventPrice();
                getAdminActions();
            case "0":
                System.out.println("Exiting...");
                exit();
                break;
            default:
                System.out.println("Sorry, that action is not for admin. Exiting application.");
                getAdminActions();
                break;
        }

    }

    private void getCounterAspect() {
        Set<Event> nameKeys = counterAspect.getNameCounter().keySet();
        System.out.println("Event 'get by name' statistics");
        if (nameKeys.size()==0) System.out.println("No events were get by name");
        for (Event name : nameKeys) {
            System.out.println("Event '" + name.getName() + "' name got " + counterAspect.getNameCounter().get(name) + " times");
        }

        Set<String> priceKeys = counterAspect.getPriceCounter().keySet();
        System.out.println("Event 'get base price' statistics");
        if (priceKeys.size()==0) System.out.println("No events were get by name");
        for (String price : priceKeys) {
            System.out.println("Event '" + price + "' price got " + counterAspect.getPriceCounter().get(price) + " times");
        }
    }

    private void getUserActions() {
        showUserCommands();
        String userCommand = readFromConsole();
        switch (userCommand) {
            case "1":
                bookTicket();
                getUserActions();
                break;
            case "2":
                getEventByName();
                getUserActions();
                break;
            case "3":
                getAllEvents();
                getUserActions();
                break;
            case "4":
                registerUser();
                getUserActions();
                break;
            case "5":
                getEventPrice();
                getAdminActions();
            case "0":
                exit();
                break;
            default:
                System.out.println("Sorry, user is not allowed to execute other commands \n" +
                        "Choose another user type to do so.");
                getUserActions();
                break;
        }
    }

    private void getEventPrice() {
        System.out.println("Enter event name:");
        String eventName = readFromConsole();
        System.out.println(eventService.getPriceByName(eventName));
    }

    private void exit() {
        System.out.println("Exiting...");
        System.exit(0);
    }

    private void bookTicket() {
        Event event = getEventByName();
        User user = getUserByEmail();
        LocalDateTime eventDate = getDateTime();
        System.out.println("Enter numbers of seats (ex: 23,24,25): ");
        String seats = readFromConsole();
        Set<Long> seatsSet = parseStringToLongSet(seats);
        try {
            double ticketsPrice = bookingService.getTicketsPrice(event, eventDate, user, seatsSet);
            double oneTicketPrice = ticketsPrice / seatsSet.size();
            Set<Ticket> ticketsForBooking = new HashSet<>();
            for (Long seat : seatsSet) {
                Ticket ticket = new Ticket(user, event, eventDate, seat);
                if (!bookingService.getPurchasedTicketsForEvent(event, eventDate).contains(ticket)) {
                    ticketsForBooking.add(ticket);
                }
            }
            bookingService.bookTickets(ticketsForBooking);
        } catch (Exception e) {
            System.out.println("Current seats may be not available.");
            bookTicket();
        }
    }

    private LocalDateTime getDateTime() {
        System.out.println("Enter date and time (ex: 2014-04-08 12:30)");
        LocalDateTime dateTime = null;
        try {
            String eventDate = readFromConsole();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            dateTime = LocalDateTime.parse(eventDate, formatter);
        } catch (Exception e) {
            System.out.println("Incorrect date and time");
            getDateTime();
        }
        return dateTime;
    }

    private Event getEventByName() {
        System.out.println("Enter event name:");
        String eventName = readFromConsole();
        Event event = null;
        try {
            event = eventService.getByName(eventName);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage() + " Please enter data again.");
            getEventByName();
        }
        assert event != null;
        System.out.println(event.toString());
        return event;
    }

    private void getAllEvents() {
        for (Event event : eventService.getAll())
            System.out.println(event.toString());
    }

    private void getAllUsers() {
        for (User user : userService.getAll())
            System.out.println(user.toString());
    }

    private void registerUser() {
        System.out.println("Enter user name:");
        String username = readFromConsole();
        System.out.println("Enter user email:");
        String userEmail = readFromConsole();
        System.out.println("Enter user birth date (dd/MM/yyyy):");
        LocalDateTime birthDate = getDateTime();
        User user = new User();
        user.setFirstName(username);
        user.setEmail(userEmail);
        user.setBirthDate(LocalDate.from(birthDate));
        try {
            userService.save(user);
        } catch (Exception e) {
            System.out.println("Something went wrong with data you entered. Please enter it again.");
            registerUser();
        }
    }

    private User getUserByEmail() {
        System.out.println("Enter user email: ");
        String userEmail = readFromConsole();
        User user = null;
        try {
            user = userService.getUserByEmail(userEmail);
            if (user == null) return null;
            System.out.println(user.toString());
        } catch (Exception e) {
            System.out.println(e.getMessage() + "\nPlease enter user email again.");
            getUserByEmail();
        }
        return user;
    }

    private void getAuditoriums() {
        for (Auditorium auditorium : auditoriumService.getAll()) {
            System.out.println(auditorium.toString());
        }
    }

    private void registerEvent() {
        System.out.println("Enter event name:");
        String eventName = readFromConsole();
        System.out.println("Enter event base ticket price");
        String strPrice = readFromConsole();
        System.out.println("Enter rating of event (High, Mid, Low. Default: Mid)");
        String ratingLevel = readFromConsole();
        System.out.println("Enter auditorium name: ");
        String auditoriumName = readFromConsole();
        LocalDateTime dateTime = getDateTime();
        NavigableMap<LocalDateTime, Auditorium> auditoriumMap = new TreeMap<>();
        auditoriumMap.put(dateTime, defineAuditorium(auditoriumName));
        Event event = new Event();
        event.setName(eventName);
        event.setBasePrice(Double.parseDouble(strPrice));
        event.setRating(EventRating.valueOf(ratingLevel.toUpperCase()));
        event.setAuditoriums(auditoriumMap);
        System.out.println(event.toString());
        try {
            eventService.save(event);
        } catch (Exception e) {
            System.out.println(e.getMessage() + "\nPlease enter event info again.");
            registerEvent();
        }
    }

    private void removeUser() {
        System.out.println("Enter user email: ");
        String email = readFromConsole();
        try {
            userService.remove(userService.getUserByEmail(email));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage() + " \nPlease enter user email again.");
            removeUser();
        }
    }

    private void removeEvent() {
        System.out.println("Enter event name:");
        String eventName = readFromConsole();
        try {
            eventService.remove(eventService.getByName(eventName));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage() + "\nPlease enter event name again.");
            removeEvent();
        }
    }

    private Auditorium defineAuditorium(String auditoriumName) {
        Auditorium auditorium = null;
        Set<Auditorium> auditoriums;
        auditoriums = auditoriumService.getAll();
        for (Auditorium auditoriumInList : auditoriums) {
            if (auditoriumInList.getName().equals(auditoriumName))
                auditorium = auditoriumInList;
        }
        return auditorium;
    }

    private Set<Long> parseStringToLongSet(String seats) {
        String[] seatsArray = seats.split(",");
        Set<Long> longSet = new HashSet<>();
        for (String s : seatsArray) {
            longSet.add(Long.valueOf(s));
        }
        return longSet;
    }

    public void populateTestData() {
        LocalDateTime localDateTime = LocalDateTime.parse("2016-04-25T20:00:00");
        LocalDateTime localDateTime2 = LocalDateTime.parse("2016-04-24T21:00:00");
        LocalDateTime localDateTime3 = LocalDateTime.parse("2016-04-24T23:00:00");
        LocalDateTime localDateTime4 = LocalDateTime.parse("2016-04-26T23:00:00");

        NavigableMap<LocalDateTime, Auditorium> auditoriums = new TreeMap<>();
        auditoriums.put(localDateTime, auditoriumService.getByName("Blue"));
        auditoriums.put(localDateTime2, auditoriumService.getByName("Green"));
        auditoriums.put(localDateTime3, auditoriumService.getByName("Green"));

        NavigableMap<LocalDateTime, Auditorium> auditoriums2 = new TreeMap<>();
        auditoriums2.put(localDateTime, auditoriumService.getByName("Green"));
        auditoriums2.put(localDateTime2, auditoriumService.getByName("Blue"));
        auditoriums2.put(localDateTime4, auditoriumService.getByName("Blue"));

        Event event1 = new Event("James Bond",
                new TreeSet<>(Arrays.asList(localDateTime, localDateTime2, localDateTime3)),
                22, EventRating.LOW, auditoriums);
        Event event2 = new Event("Martian",
                new TreeSet<>(Arrays.asList(localDateTime, localDateTime2, localDateTime4)),
                50, EventRating.HIGH, auditoriums2);
        Map<Long, Event> eventMap = new HashMap<>();
        eventMap.put(0L, event1);
        eventMap.put(1L, event2);
        eventService.populateTestData(eventMap);
        User user1 = new User("Bebek", "Kekeb", "bebek@com.ua", LocalDate.parse("1990-04-24"));
        User user2 = new User("John", "Silver", "black@com.ua", LocalDate.parse("2000-01-01"));
        User user3 = new User("Capitan", "Flint", "black2@com.ua", LocalDate.parse("1998-04-22"));
        Ticket ticket1 = new Ticket(user1, event1, localDateTime, 8);
        Ticket ticket2 = new Ticket(user1, event1, localDateTime, 9);
        Ticket ticket3 = new Ticket(user1, event1, localDateTime, 10);
        Ticket ticket4 = new Ticket(user1, event1, localDateTime, 11);
        Ticket ticket5 = new Ticket(user1, event2, localDateTime4, 1);
        Ticket ticket6 = new Ticket(user1, event2, localDateTime4, 2);
        Ticket ticket7 = new Ticket(user1, event2, localDateTime4, 3);
        Ticket ticket8 = new Ticket(user1, event2, localDateTime4, 4);
        Ticket ticket9 = new Ticket(user1, event2, localDateTime4, 5);
        NavigableSet<Ticket> tickets = new TreeSet<>(Arrays.asList(ticket1, ticket2, ticket3, ticket4, ticket5, ticket6, ticket7, ticket8, ticket9));
        user1.setTickets(tickets);
        Map<Long, User> userMap = new HashMap<>();
        userMap.put(0L, user1);
        userMap.put(1L, user2);
        userMap.put(2L, user3);
        userService.populateTestData(userMap);
    }


    public BookingService getBookingService() {
        return bookingService;
    }

    public void setBookingService(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    public AuditoriumService getAuditoriumService() {
        return auditoriumService;
    }

    public void setAuditoriumService(AuditoriumService auditoriumService) {
        this.auditoriumService = auditoriumService;
    }

    public EventService getEventService() {
        return eventService;
    }

    public void setEventService(EventService eventService) {
        this.eventService = eventService;
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public DiscountService getDiscountService() {
        return discountService;
    }

    public void setDiscountService(DiscountService discountService) {
        this.discountService = discountService;
    }
}
