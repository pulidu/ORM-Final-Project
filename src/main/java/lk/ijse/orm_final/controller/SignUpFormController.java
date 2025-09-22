package lk.ijse.orm_final.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.orm_final.bo.BOFactory;
import lk.ijse.orm_final.bo.custom.SignUpBO;
import lk.ijse.orm_final.dto.UserDTO;
import lk.ijse.orm_final.exception.ExceptionHandler;
import lk.ijse.orm_final.exception.UserAlreadyExistsException;
import lk.ijse.orm_final.util.PasswordStorage;
import lk.ijse.orm_final.util.Regex;

import java.io.IOException;

public class SignUpFormController {


    @FXML
    private CheckBox adminCheckBox;

    @FXML
    private CheckBox admissionCheckBox;

    @FXML
    private TextField inputPassword;

    @FXML
    private TextField inputUserName;

    @FXML
    private AnchorPane signUpForm;

    SignUpBO signUpBO = (SignUpBO) BOFactory.getBO(BOFactory.BOType.SIGNUP);

    @FXML
    void adminCheckBoxOnAction(ActionEvent event) {
        adminCheckBox.setSelected(true);
        admissionCheckBox.setSelected(false);
    }

    @FXML
    void admissionCheckBoxOnAction(ActionEvent event) {
        admissionCheckBox.setSelected(true);
        adminCheckBox.setSelected(false);
    }

    @FXML
    void backToLoginOnAction(MouseEvent event) {
        try {
            Scene scene = new Scene(FXMLLoader.load(this.getClass().getResource("/loginForm.fxml")));
            Stage stage = (Stage) signUpForm.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void inputUserNameOnAction(ActionEvent event) {
        inputPassword.requestFocus();
    }

    @FXML
    void signUpBtnOnAction(ActionEvent event) {
        if (isValied()){
            UserDTO userDTO = new UserDTO();
            userDTO.setUserName(inputUserName.getText().trim());
            userDTO.setPassword(PasswordStorage.hashPassword(inputPassword.getText().trim()));

            if (adminCheckBox.isSelected()) {
                userDTO.setRole("Admin");
            } else {
                userDTO.setRole("Admissions Coordinator");
            }

            try {
                signUpBO.signUp(userDTO);
            } catch (UserAlreadyExistsException e) {
                ExceptionHandler.handleException(e);
            }

            inputUserName.clear();
            inputPassword.clear();
            adminCheckBox.setSelected(false);
            admissionCheckBox.setSelected(false);
        } else {
            new Alert(Alert.AlertType.WARNING,"Please Enter All Fields !!").show();
        }
    }

    public boolean isValied() {
        return !inputUserName.getText().isEmpty() && !inputPassword.getText().isEmpty() && (adminCheckBox.isSelected() || admissionCheckBox.isSelected());
    }

}