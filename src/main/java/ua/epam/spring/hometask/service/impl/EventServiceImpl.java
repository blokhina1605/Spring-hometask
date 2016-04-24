package ua.epam.spring.hometask.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.epam.spring.hometask.daos.EventDao;
import ua.epam.spring.hometask.daos.impl.EventDaoImpl;
import ua.epam.spring.hometask.domain.Event;
import ua.epam.spring.hometask.domain.User;
import ua.epam.spring.hometask.service.EventService;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * @author Yevheniia_Blokhina.
 */

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private EventDao eventDao;

    public void populateTestData(Map<Long, Event> userMap) {
        eventDao.populateTestData(userMap);
    }

    @Nullable
    @Override
    public Event getByName(@Nonnull String name) {
        return eventDao.findByName(name);
    }

    @Nonnull
    @Override
    public Set<Event> getForDateRange(@Nonnull LocalDate from, @Nonnull LocalDate to) {
        return eventDao.getForDateRange(from, to);
    }

    @Nonnull
    @Override
    public Set<Event> getNextEvents(@Nonnull LocalDateTime to) {
        return eventDao.getNextEvents(to);
    }

    @Override
    public Event save(@Nonnull Event object) {
        return eventDao.create(object);
    }

    @Override
    public void remove(@Nonnull Event object) {
        eventDao.delete(object);
    }

    @Override
    public Event getById(@Nonnull Long id) {
        return eventDao.getById(id);
    }

    @Nonnull
    @Override
    public Collection<Event> getAll() {
        return eventDao.getAll();
    }

    public EventDao getEventDao() {
        return eventDao;
    }

    public void setEventDao(EventDao eventDao) {
        this.eventDao = eventDao;
    }
}
