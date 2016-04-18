package ua.epam.spring.hometask.daos.impl;

import ua.epam.spring.hometask.daos.AuditoriumDao;
import ua.epam.spring.hometask.domain.Auditorium;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

/**
 * @author Yevheniia_Blokhina.
 */
public class AuditoriumDaoImpl implements ua.epam.spring.hometask.daos.AuditoriumDao {

    private Map<Long, Auditorium> entityMap = Collections.EMPTY_MAP;

    @Override
    public Auditorium findByName(String name) {
        for (Auditorium auditorium : findAll()) {
            if (auditorium.getName().equals(name)) return auditorium;
        }

        return null;
    }

    @Override
    public Set<Auditorium> findAll() {
        return (Set<Auditorium>) entityMap.values();
    }
}
