package lk.ijse.orm_final.dao.custom;


import lk.ijse.orm_final.dao.SuperDAO;
import lk.ijse.orm_final.entity.User;
import lk.ijse.orm_final.exception.UserAlreadyExistsException;

import java.util.List;

public interface UserDAO extends SuperDAO {
    void save(User user) throws UserAlreadyExistsException;

    void update(User user);

    void delete(User user);

    User getUser(String userName);

    List<User> getAllUsers();
}