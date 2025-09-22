package lk.ijse.orm_final.bo.custom;

import lk.ijse.orm_final.bo.SuperBO;
import lk.ijse.orm_final.dto.UserDTO;
import lk.ijse.orm_final.exception.UserAlreadyExistsException;
public interface SignUpBO extends SuperBO {

    void signUp(UserDTO userDTO) throws UserAlreadyExistsException;
}
