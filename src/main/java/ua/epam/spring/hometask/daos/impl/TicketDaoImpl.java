package ua.epam.spring.hometask.daos.impl;

import org.springframework.stereotype.Repository;
import ua.epam.spring.hometask.daos.GenericDaoImpl;
import ua.epam.spring.hometask.daos.TicketDao;
import ua.epam.spring.hometask.domain.Ticket;
import ua.epam.spring.hometask.domain.User;

import java.util.Collections;
import java.util.Map;

/**
 * @author Yevheniia_Blokhina.
 */
@Repository
public class TicketDaoImpl extends GenericDaoImpl<Ticket> implements TicketDao {

    public Map<Long, Ticket> getEntityMap() {
        return entityMap;
    }

    public void setEntityMap(Map<Long, Ticket> entityMap) {
        this.entityMap = entityMap;
    }
}
