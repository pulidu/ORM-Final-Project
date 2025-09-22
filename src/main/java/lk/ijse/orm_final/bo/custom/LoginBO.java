package lk.ijse.orm_final.bo.custom;

import lk.ijse.orm_final.dto.UserDTO;

import java.io.InvalidClassException;

public interface LoginBO {
    UserDTO getUser(String username) throws InvalidClassException;
}
