package lk.ijse.orm_final.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.orm_final.bo.BOFactory;
import lk.ijse.orm_final.bo.custom.StudentBO;
import lk.ijse.orm_final.dto.StudentDTO;
import lk.ijse.orm_final.tdm.StudentTm;
import lk.ijse.orm_final.util.Regex;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

public class StudentFormController {

    public TextField txtSearch;
    @FXML private TableColumn<?, ?> colAddress;
    @FXML private TableColumn<?, ?> colId;
    @FXML private TableColumn<?, ?> colName;
    @FXML private TableColumn<?, ?> colRegisterDate;
    @FXML private TableColumn<?, ?> colTel;

    @FXML private DatePicker registerDatePicker;
    @FXML private AnchorPane studentForm;
    @FXML private TableView<StudentTm> tblStudent;
    @FXML private TextField txtAddress;
    @FXML private TextField txtId;
    @FXML private TextField txtName;
    @FXML private TextField txtTel;

    StudentBO studentBO = (StudentBO) BOFactory.getBO(BOFactory.BOType.STUDENT);

    public void initialize() {
        setCellValueFactory();
        loadAllStudent();
        generateStudentId();
    }

    private void loadAllStudent() {
        List<StudentDTO> allStudent = studentBO.getAllStudent();
        ObservableList<StudentTm> studentTms = FXCollections.observableArrayList();

        for (StudentDTO studentDTO : allStudent) {
            studentTms.add(new StudentTm(
                    studentDTO.getStudentId(),
                    studentDTO.getName(),
                    studentDTO.getAddress(),
                    studentDTO.getTel(),
                    (Date) studentDTO.getRegistrationDate(),
                    null
            ));
        }
        tblStudent.setItems(studentTms);
    }

    private void setCellValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colTel.setCellValueFactory(new PropertyValueFactory<>("tel"));
        colRegisterDate.setCellValueFactory(new PropertyValueFactory<>("registrationDate"));
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearData();
    }

    private void clearData() {
        txtId.clear();
        txtName.clear();
        txtAddress.clear();
        txtTel.clear();
        registerDatePicker.setValue(null);
    }

    private StudentDTO getObject() {
        return new StudentDTO(
                txtId.getText(),
                txtName.getText(),
                txtAddress.getText(),
                Long.parseLong(txtTel.getText()),
                Date.valueOf(registerDatePicker.getValue())
        );
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        if (isValidStudent()) {
            studentBO.deleteStudent(getObject());
            loadAllStudent();
            clearData();
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        if (isValidStudent()) {
            studentBO.saveStudent(getObject());
            clearData();
            loadAllStudent();

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/paymentForm.fxml"));
                Parent root = loader.load();

                Stage stage = new Stage();
                stage.setTitle("Payment Form");
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR, "Failed to open Payment Form!").show();
            }

        } else {
            new Alert(Alert.AlertType.WARNING, "Please Enter All Fields !!").show();
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        if (isValidStudent()) {
            studentBO.updateStudent(getObject());
            clearData();
            loadAllStudent();
        }
    }

    @FXML
    void tblStudentOnClickAction(MouseEvent event) {
        StudentTm selectedItem = tblStudent.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            txtId.setText(selectedItem.getStudentId());
            txtName.setText(selectedItem.getName());
            txtAddress.setText(selectedItem.getAddress());
            txtTel.setText(String.valueOf(selectedItem.getTel()));
            registerDatePicker.setValue(selectedItem.getRegistrationDate().toLocalDate());
        }
    }


    public boolean isValidStudent() {
        if (!Regex.setTextColor(Regex.FieldType.STUDENTID, txtId)) return false;
        if (!Regex.setTextColor(Regex.FieldType.NAME, txtName)) return false;
        if (!Regex.setTextColor(Regex.FieldType.ADDRESS, txtAddress)) return false;
        if (!Regex.setTextColor(Regex.FieldType.TEL, txtTel)) return false;
        if (txtId.getText().isEmpty() || registerDatePicker.getValue() == null) return false;
        return true;
    }


    @FXML void txtAddressKeyAction(KeyEvent event) {
        Regex.setTextColor(Regex.FieldType.ADDRESS, txtAddress);
    }

    @FXML void txtNameKeyAction(KeyEvent event) {
        Regex.setTextColor(Regex.FieldType.NAME, txtName);
    }

    @FXML void txtTelKeyAction(KeyEvent event) {
        Regex.setTextColor(Regex.FieldType.TEL, txtTel);
    }

    @FXML void txtIdKeyAction(KeyEvent event) {
        Regex.setTextColor(Regex.FieldType.STUDENTID, txtId);
    }

    @FXML
    public void txtSearchKeyReleased(KeyEvent keyEvent) {
        String searchText = txtSearch.getText().toLowerCase();
        List<StudentDTO> allStudent = studentBO.getAllStudent();
        ObservableList<StudentTm> filteredList = FXCollections.observableArrayList();

        for (StudentDTO studentDTO : allStudent) {
            if (studentDTO.getStudentId().toLowerCase().contains(searchText) ||
                    studentDTO.getName().toLowerCase().contains(searchText)) {
                filteredList.add(new StudentTm(
                        studentDTO.getStudentId(),
                        studentDTO.getName(),
                        studentDTO.getAddress(),
                        studentDTO.getTel(),
                        (Date) studentDTO.getRegistrationDate(),
                        null
                ));
            }
        }
        tblStudent.setItems(filteredList);
    }

    private void generateStudentId() {
        String newId = studentBO.generateNewId();
        txtId.setText(newId);
        txtId.setEditable(false);
    }
}