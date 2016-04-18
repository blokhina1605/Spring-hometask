package ua.epam.spring.hometask.daos.impl;

import org.joda.time.Days;
import org.joda.time.DurationFieldType;
import ua.epam.spring.hometask.daos.EventDao;
import ua.epam.spring.hometask.daos.GenericDaoImpl;
import ua.epam.spring.hometask.domain.Event;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import static java.time.LocalDateTime.now;
import static java.time.temporal.ChronoUnit.DAYS;

/**
 * @author Yevheniia_Blokhina.
 */
public class EventDaoImpl extends GenericDaoImpl<Event> implements ua.epam.spring.hometask.daos.EventDao {

    @Override
    public Event findByName(String name) {
        for (Event event : getAll()) {
            if (event.getName().equals(name)) return event;
        }
        return null;
    }

    @Override
    public Set<Event> getForDateRange(LocalDate from, LocalDate to) {
        return getEvents(from.atStartOfDay(), to.atStartOfDay());
    }

    @Override
    public Set<Event> getNextEvents(LocalDateTime to) {
        LocalDateTime from = now();
        return getEvents(from, to);
    }

    private Set<Event> getEvents(LocalDateTime from, LocalDateTime to) {
        Collection<Event> all = getAll();
        Set<Event> events = new HashSet<>();
        boolean flag = false;
        for (Event event : all) {
            for (LocalDateTime localDateTime : event.getAirDates()) {
                if (localDateTime.isAfter(ChronoLocalDateTime.from(to))
                        || localDateTime.isBefore(ChronoLocalDateTime.from(from))) {
                    flag = false;
                    break;
                } else {
                    flag = true;
                }
            }
            if (flag) events.add(event);
        }
        return events;
    }
}