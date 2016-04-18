package ua.epam.spring.hometask.daos;

import ua.epam.spring.hometask.domain.Auditorium;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * @author Yevheniia_Blokhina.
 */
public interface AuditoriumDao {

    Auditorium findByName(String name);

    Set<Auditorium> findAll();

}
