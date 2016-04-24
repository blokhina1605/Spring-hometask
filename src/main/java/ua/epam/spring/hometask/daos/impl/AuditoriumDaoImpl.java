package ua.epam.spring.hometask.daos.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import ua.epam.spring.hometask.daos.AuditoriumDao;
import ua.epam.spring.hometask.domain.Auditorium;

import javax.annotation.Resource;
import javax.annotation.Resources;
import java.util.*;

/**
 * @author Yevheniia_Blokhina.
 */

@Repository
public class AuditoriumDaoImpl implements ua.epam.spring.hometask.daos.AuditoriumDao {

    //@Resource(name = "auditoriumMap")
    private Map<Long, Auditorium> auditoriumMap = Collections.EMPTY_MAP;

    private static long counter = 0;

    public AuditoriumDaoImpl() {
        auditoriumMap = new HashMap<>();
        Auditorium auditorium1 = new Auditorium("Blue", 16, new HashSet(Arrays.asList(1, 2, 3, 4)));
        Auditorium auditorium2 = new Auditorium("Green", 30, new HashSet(Arrays.asList(1, 2, 3, 4, 8, 9, 10, 11)));
        auditoriumMap.put(counter++, auditorium1);
        auditoriumMap.put(counter++, auditorium2);
    }

    @Override
    public Auditorium findByName(String name) {
        for (Auditorium auditorium : findAll()) {
            if (auditorium.getName().equals(name)) return auditorium;
        }

        return null;
    }

    @Override
    public Set<Auditorium> findAll() {
        Set<Auditorium> auditoriumSet = new HashSet<>();
        auditoriumSet.addAll(auditoriumMap.values());
        return auditoriumSet;
    }

    public Map<Long, Auditorium> getAuditoriumMap() {
        return auditoriumMap;
    }

    public void setAuditoriumMap(Map<Long, Auditorium> auditoriumMap) {
        this.auditoriumMap = auditoriumMap;
    }
}
