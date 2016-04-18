package ua.epam.spring.hometask.daos;

import ua.epam.spring.hometask.domain.Event;

import javax.annotation.Nonnull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * @author Yevheniia_Blokhina.
 */
public interface EventDao extends GenericDao<Event> {

    Event findByName(String name);

    Set<Event> getForDateRange(LocalDate from, LocalDate to);

    Set<Event> getNextEvents(LocalDateTime to);
}
