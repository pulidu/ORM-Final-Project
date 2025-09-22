package lk.ijse.orm_final.bo.custom;

import lk.ijse.orm_final.bo.SuperBO;
import lk.ijse.orm_final.dto.UserDTO;

import java.util.List;

public interface SettingBO extends SuperBO {


    List<UserDTO> getAllUsers();
    void deleteUser(UserDTO userDTO);
    void updateUser(UserDTO userDTO);
}
