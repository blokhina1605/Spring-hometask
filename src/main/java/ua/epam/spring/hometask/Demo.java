package ua.epam.spring.hometask;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ua.epam.spring.hometask.domain.Auditorium;
import ua.epam.spring.hometask.service.*;

import java.io.File;

/**
 * @author Yevheniia_Blokhina.
 */
public class Demo {

    private BookingService bookingService;

    private AuditoriumService auditoriumService;

    private EventService eventService;

    private UserService userService;

    private DiscountService discountService;


//    public void showGreetings() {
//        System.out.print("Hi! Please, enter your role to start work. \n" +
//                "  - admin \n" +
//                "  - user \n" +
//                "Role: ");
//    }
//
//    public void showUserCommands() {
//        System.out.print("Available commands for user: \n" +
//                "  - book ticket \n" +
//                "  - get event by name \n" +
//                "  - get all events \n" +
//                "  - register user \n" +
//                "  - change user \n" +
//                "  - exit \n" +
//                "Command: ");
//    }
//
//    public void showAdminCommands() {
//        System.out.print("Available commands for admin: \n" +
//                "  - create event \n" +
//                "  - book ticket \n" +
//                "  - get event by name \n" +
//                "  - get all events \n" +
//                "  - register user \n" +
//                "  - get user by name \n" +
//                "  - get user by email \n" +
//                "  - get auditoriums \n" +
//                "  - remove user \n" +
//                "  - remove event \n" +
//                "  - change user \n" +
//                "  - get event stats\n" +
//                "  - get discount stats\n" +
//                "  - check user is lucky\n" +
//                "  - exit \n" +
//                "Command: ");
//    }
//
//    public void run() {
//        chooseUser();
//    }
//
//    private void chooseUser() {
//        showGreetings();
//        String userRole = ConsoleUtils.readFromConsole();
//        if (userRole.equals("admin")) {
//            handleAdminActions();
//        }
//        if (userRole.equals("user")) {
//            handleUserActions();
//        }
//    }
//
//    private void handleAdminActions() {
//        showAdminCommands();
//        String adminCommand = ConsoleUtils.readFromConsole();
//        if (adminCommand.equals("book ticket")) {
//            bookTicket();
//            chooseUser();
//        } else if (adminCommand.equals("get event by name")) {
//            getEventByName();
//            chooseUser();
//        } else if (adminCommand.equals("get all events")) {
//            getAllEvents();
//            chooseUser();
//        } else if (adminCommand.equals("register user")) {
//            registerUser();
//            chooseUser();
//        } else if (adminCommand.equals("create event")) {
//            registerEvent();
//            chooseUser();
//        } else if (adminCommand.equals("get user by name")) {
//            getUsersByName();
//            chooseUser();
//        } else if (adminCommand.equals("get user by email")) {
//            getUserByEmail();
//            chooseUser();
//        } else if (adminCommand.equals("get auditoriums")) {
//            getAuditoriums();
//            chooseUser();
//        } else if (adminCommand.equals("remove user")) {
//            removeUser();
//            chooseUser();
//        } else if (adminCommand.equals("remove event")) {
//            removeEvent();
//            chooseUser();
//        } else if (adminCommand.equals("change user")) {
//            chooseUser();
//        } else if (adminCommand.equals("exit")) {
//            System.out.println("Exiting...");
//        } else if (adminCommand.equals("get event stats")) {
//            getEventStatistics();
//            chooseUser();
//        } else if (adminCommand.equals("get discount stats")) {
//            getDiscountStatistics();
//            chooseUser();
//        } else if (adminCommand.equals("check user is lucky")) {
//            checkUserIsLucky();
//            chooseUser();
//        } else {
//            System.out.println("Sorry, that action is not for admin. Exiting application.");
//            exit();
//        }
//    }
//
//    private void handleUserActions() {
//        showUserCommands();
//        String userCommand = ConsoleUtils.readFromConsole();
//        if (userCommand.equals("book ticket")) {
//            bookTicket();
//            chooseUser();
//        } else if (userCommand.equals("get event by name")) {
//            getEventByName();
//            chooseUser();
//        } else if (userCommand.equals("get all events")) {
//            getAllEvents();
//            chooseUser();
//        } else if (userCommand.equals("register user")) {
//            registerUser();
//            chooseUser();
//        } else if (userCommand.equals("exit")) {
//            exit();
//        } else if (userCommand.equals("change user")) {
//            chooseUser();
//        } else {
//            System.out.println("Sorry, user is not allowed to execute other commands \n" +
//                    "Choose another user type to do so.");
//            chooseUser();
//        }
//    }
//
//    private void checkUserIsLucky() {
//        System.out.println("Please enter user email");
//        String email = ConsoleUtils.readFromConsole();
//        if (userService.checkUserIsLucky(email))
//            System.out.println(" User is lucky");
//        System.out.println(" User is not lucky");
//    }
//
//    private void getEventStatistics() {
//        getByNameEventStatistics();
//        getTicketPriceEventStatistics();
//    }
//
//    private void getDiscountStatistics() {
//        getAllDiscountsStatistics();
//        getDiscountForEachuserStats();
//    }
//    private void getByNameEventStatistics(){
//        Set<String> nameKeys = counterAspect.getNameCounter().keySet();
//        System.out.println("Event 'getByName' statistics");
//        for (String name : nameKeys) {
//            System.out.println(" Event '" + name + "' got " + counterAspect.getNameCounter().get(name) + " times");
//        }
//    }
//
//    private void getTicketPriceEventStatistics() {
//        Set<Integer> priceKeys = counterAspect.getPriceCounter().keySet();
//        System.out.println("Event 'getTicketPrice' statistics");
//        for (Integer price : priceKeys) {
//            System.out.println(" Event price '" + price + "' got " + counterAspect.getPriceCounter().get(price) + " times");
//        }
//    }
//
//    private void getAllDiscountsStatistics() {
//        Set<Double> discountKeys = discountAspect.getTotalDiscountCounter().keySet();
//        System.out.println("Statistics for all discounts given");
//        for (Double discount : discountKeys) {
//            System.out.println(" Discount '" + discount + "' has been given " + discountAspect.getTotalDiscountCounter().get(discount) + " times");
//        }
//    }
//
//    private void getDiscountForEachuserStats() {
//        Set<Double> discountKeys = discountAspect.getUserDiscountCounter().keySet();
//        System.out.println("Statistics for discounts given to each user");
//        for (Double discount : discountKeys) {
//            System.out.println(" Discount '" + discount + "'");
//            Set<String> userNames = discountAspect.getUserDiscountCounter().get(discount).keySet();
//            for (String name : userNames) {
//                System.out.println("   User '" + name + "' had that discount " + discountAspect.getUserDiscountCounter().get(discount).get(name) + " times");
//            }
//        }
//    }
//
//    private void exit() {
//        System.out.println("Exiting...");
//    }
//
//    private void bookTicket() {
//        Event event = getEventByName();
//        User user = getUserByEmail();
//        System.out.println("Enter event date (dd/MM/yyyy):");
//        String eventDate = ConsoleUtils.readFromConsole();
//        System.out.println("Enter numbers of seats (ex: 23,24,25): ");
//        String seats = ConsoleUtils.readFromConsole();
//        String[] seatsList = parseStringToArr(seats);
//        try {
//            double ticketsPrice = bookingService.getTicketPrice(event, eventDate, user, seatsList);
//            double oneTicketPrice = ticketsPrice / seatsList.length;
//
//            for (String seat : seatsList) {
//                Ticket ticket = new Ticket(event.getEventName(), Integer.parseInt(seat), user.getEmail(), oneTicketPrice, eventDate);
//                if (!bookingService.getTicketsForEvent(event, eventDate).contains(ticket)) {
//                    bookingService.bookTicket(user, ticket);
//                    userService.incrementUserBookedTickets(user.getName());
//                }
//            }
//        } catch (IllegalArgumentException | ParseException e) {
//            System.out.println(e.getMessage());
//            bookTicket();
//        }
//    }
//
//    private Event getEventByName() {
//        System.out.println("Enter event name:");
//        String eventName = ConsoleUtils.readFromConsole();
//        Event event = null;
//        try {
//            event = eventService.getByName(eventName);
//        } catch (IllegalArgumentException e) {
//            System.out.println(e.getMessage() + " Please enter data again.");
//            getEventByName();
//        }
//        assert event != null;
//        System.out.println(event.toString());
//        return event;
//    }
//
//    private void getAllEvents() {
//        for (Event event : eventService.getAll())
//            System.out.println(event.toString());
//    }
//
//    private void registerUser() {
//        System.out.println("Enter user name:");
//        String username = ConsoleUtils.readFromConsole();
//        System.out.println("Enter user email:");
//        String userEmail = ConsoleUtils.readFromConsole();
//        System.out.println("Enter user birth date (dd/MM/yyyy):");
//        String birthDate = ConsoleUtils.readFromConsole();
//        try {
//            userService.register(username, birthDate, userEmail);
//        } catch (IllegalArgumentException | ParseException e) {
//            System.out.println("Something went wrong with data you entered. Please enter it again.");
//            registerUser();
//        }
//    }
//
//    private List<User> getUsersByName() {
//        System.out.println("Enter user name: ");
//        String userName = ConsoleUtils.readFromConsole();
//        List<User> users = null;
//        try {
//            users = userService.getUsersByName(userName);
//            for (User user : users) {
//                System.out.println(user.toString());
//            }
//        } catch (IllegalArgumentException e) {
//            System.out.println(e.getMessage() + "\n Please enter users name again.");
//            getUsersByName();
//        }
//        return users;
//    }
//
//    private User getUserByEmail() {
//        System.out.println("Enter user email: ");
//        String userEmail = ConsoleUtils.readFromConsole();
//        User user = null;
//        try {
//            user = userService.getUserByEmail(userEmail);
//            System.out.println(user.toString());
//        } catch (IllegalArgumentException e) {
//            System.out.println(e.getMessage() + "\nPlease enter user email again.");
//            getUserByEmail();
//        }
//        return user;
//    }
//
//    private void getAuditoriums() {
//        for (Auditorium auditorium : auditoriumService.getAuditoriums()) {
//            System.out.println(auditorium.toString());
//        }
//    }
//
//    private void registerEvent() {
//        System.out.println("Enter event name:");
//        String eventName = ConsoleUtils.readFromConsole();
//        System.out.println("Enter event base ticket price");
//        String strPrice = ConsoleUtils.readFromConsole();
//        System.out.println("Enter rating of event (High, Mid, Low. Default: Mid)");
//        String ratingLevel = ConsoleUtils.readFromConsole();
//        System.out.println("Enter auditorium name: ");
//        String auditoriumName = ConsoleUtils.readFromConsole();
//        int price = Integer.parseInt(strPrice);
//        try {
//            Auditorium auditorium = defineAuditorium(auditoriumName);
//            eventService.create(eventName, price, ratingLevel, auditorium);
//        } catch (IllegalArgumentException e) {
//            System.out.println(e.getMessage() + "\nPlease enter event info again.");
//            registerEvent();
//        }
//    }
//
//    private void removeUser() {
//        System.out.println("Enter user email: ");
//        String email = ConsoleUtils.readFromConsole();
//        try {
//            userService.remove(email);
//        } catch (IllegalArgumentException e) {
//            System.out.println(e.getMessage() + " \nPlease enter user email again.");
//            removeUser();
//        }
//    }
//
//    private void removeEvent() {
//        System.out.println("Enter event name:");
//        String eventName = ConsoleUtils.readFromConsole();
//        try {
//            eventService.remove(eventName);
//        } catch (IllegalArgumentException e) {
//            System.out.println(e.getMessage() + "\nPlease enter event name again.");
//            removeEvent();
//        }
//    }
//
//    private Auditorium defineAuditorium(String auditoriumName) {
//        Auditorium auditorium = null;
//        List<Auditorium> auditoriums;
//        auditoriums = auditoriumService.getAuditoriums();
//        for (Auditorium auditoriumInList : auditoriums) {
//            if (auditoriumInList.getName().equals(auditoriumName))
//                auditorium = auditoriumInList;
//        }
//        return auditorium;
//    }
//
//    private String[] parseStringToArr(String seats) {
//        return seats.split(",");
//    }
}
