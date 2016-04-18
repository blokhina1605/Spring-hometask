package ua.epam.spring.hometask.daos.impl;

import ua.epam.spring.hometask.daos.GenericDaoImpl;
import ua.epam.spring.hometask.daos.UserDao;
import ua.epam.spring.hometask.domain.User;

/**
 * @author Yevheniia_Blokhina.
 */
public class UserDaoImpl extends GenericDaoImpl<User> implements ua.epam.spring.hometask.daos.UserDao {

    @Override
    public User getUserByEmail(String email) {
        for (User user : getAll()) {
            if (user.getEmail().equals(email)) return user;
        }

        return null;
    }
}
