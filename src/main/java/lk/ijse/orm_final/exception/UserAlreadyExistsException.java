package lk.ijse.orm_final.exception;

public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException(String message) {

        super(message);
    }
}