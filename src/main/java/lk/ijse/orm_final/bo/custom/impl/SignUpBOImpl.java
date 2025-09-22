package lk.ijse.orm_final.bo.custom.impl;

import lk.ijse.orm_final.bo.custom.SignUpBO;
import lk.ijse.orm_final.dao.DAOFactory;
import lk.ijse.orm_final.dao.custom.UserDAO;
import lk.ijse.orm_final.dto.UserDTO;
import lk.ijse.orm_final.entity.User;
import lk.ijse.orm_final.exception.UserAlreadyExistsException;

import lk.ijse.orm_final.bo.custom.SettingBO;
import lk.ijse.orm_final.bo.custom.SignUpBO;

public class SignUpBOImpl implements SignUpBO {

    UserDAO userDAO = (UserDAO) DAOFactory.getDAO(DAOFactory.DAOType.USER);

    @Override
    public void signUp(UserDTO userDTO) throws UserAlreadyExistsException {
        User user = new User(userDTO.getUserName(), userDTO.getPassword(), userDTO.getRole());
        userDAO.save(user);
    }
}