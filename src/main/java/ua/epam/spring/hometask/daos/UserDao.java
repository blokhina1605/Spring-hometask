package ua.epam.spring.hometask.daos;

import ua.epam.spring.hometask.domain.User;

/**
 * @author Yevheniia_Blokhina.
 */

public interface UserDao extends GenericDao<User> {

    User getUserByEmail(String email);

}
