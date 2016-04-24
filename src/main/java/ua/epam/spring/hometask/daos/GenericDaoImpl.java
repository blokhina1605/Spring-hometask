package ua.epam.spring.hometask.daos;

import ua.epam.spring.hometask.domain.DomainObject;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;

/**
 * @author Yevheniia_Blokhina.
 */
public abstract class GenericDaoImpl<T extends DomainObject> implements GenericDao<T> {

    protected Map<Long, T> entityMap = Collections.EMPTY_MAP;

    @Override
    public T create(T t) {
        entityMap.put(t.getId(), t);
        return t;
    }

    @Override
    public void delete(T id) {
        entityMap.remove(id.getId());
    }

    @Override
    public T getById(Long id) {
        return entityMap.get(id);
    }

    @Override
    public T update(T t) {
        entityMap.put(t.getId(), t);
        return t;
    }

    @Override
    public Collection<T> getAll() {
        return entityMap.values();
    }

    @Override
    public void populateTestData(Map<Long, T> entityMap) {
        this.entityMap = entityMap;
    }

    public Map<Long, T> getEntityMap() {
        return entityMap;
    }

    public void setEntityMap(Map<Long, T> entityMap) {
        this.entityMap = entityMap;
    }

}
