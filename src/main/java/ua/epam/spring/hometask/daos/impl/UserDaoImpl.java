package ua.epam.spring.hometask.daos.impl;

import org.springframework.stereotype.Repository;
import ua.epam.spring.hometask.daos.GenericDaoImpl;
import ua.epam.spring.hometask.daos.UserDao;
import ua.epam.spring.hometask.domain.Auditorium;
import ua.epam.spring.hometask.domain.User;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Yevheniia_Blokhina.
 */
@Repository
public class UserDaoImpl extends GenericDaoImpl<User> implements UserDao {

  //  private Map<Long, User> userMap = new HashMap<>();

    @Override
    public User getUserByEmail(String email) {
        for (User user : getAll()) {
            if (user.getEmail().equalsIgnoreCase(email)) return user;
        }

        return null;
    }

    public Map<Long, User> getUserMap() {
        return entityMap;
    }

    public void setUserMap(Map<Long, User> userMap) {
        this.entityMap = userMap;
    }
}
