package ua.epam.spring.hometask.daos;

import java.util.Collection;
import java.util.Map;


/**
 * @author Yevheniia_Blokhina.
 */
public interface GenericDao<T> {

    T create(T t);

    void delete(T id);

    T getById(Long id);

    T update(T t);

    Collection<T> getAll();

    void populateTestData(Map<Long, T> entityMap);
}
