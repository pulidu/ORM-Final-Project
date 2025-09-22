package lk.ijse.orm_final.controller;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lk.ijse.orm_final.bo.BOFactory;
import lk.ijse.orm_final.bo.custom.ViewAllBO;
import lk.ijse.orm_final.dto.courseDTO;
import lk.ijse.orm_final.entity.Payment;
import lk.ijse.orm_final.entity.Student;
import lk.ijse.orm_final.tdm.StudentTm;
import lk.ijse.orm_final.tdm.ViewAllTm;

import java.io.IOException;
import java.util.List;

public class ViewAllFormController {

    @FXML
    private TableColumn<?, ?> colBalance;

    @FXML
    private TableColumn<?, ?> colPayment;

    @FXML
    private TableColumn<?, ?> colInstallment;

    @FXML
    private TableColumn<?, ?> colRegisterDate;

    @FXML
    private TableColumn<?, ?> colStudentId;

    @FXML
    private TableColumn<?, ?> colStudentName;

    @FXML
    private Label lblStudentCount;

    @FXML
    private ChoiceBox<String> selectPrgramChoiceBox;

    @FXML
    private TableView<ViewAllTm> viewTbl;

    ViewAllBO viewAllBO = (ViewAllBO) BOFactory.getBO(BOFactory.BOType.VIEWALL);

    public void initialize() {
        setChoiceBoxData();
        setChoiceBoxAction();
        setCellValueFactory();
    }

    private void setChoiceBoxAction() {
        selectPrgramChoiceBox.setOnAction(event -> {
            // Get the selected item
            String programName = selectPrgramChoiceBox.getValue();

            loadTableData(programName.trim());
        });
    }

    private void setCellValueFactory() {
        colStudentId.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        colStudentName.setCellValueFactory(new PropertyValueFactory<>("studentName"));
        colRegisterDate.setCellValueFactory(new PropertyValueFactory<>("registerDate"));
        colInstallment.setCellValueFactory(new PropertyValueFactory<>("installment"));
        colBalance.setCellValueFactory(new PropertyValueFactory<>("balance"));
        colPayment.setCellValueFactory(new PropertyValueFactory<>("payment"));
    }

    private void loadTableData(String programName) {
        viewTbl.getItems().clear();

        List<Object[]> allEqualByProgramName = viewAllBO.getAllEqualByProgramName(programName);
        ObservableList<ViewAllTm> viewAllTms = viewTbl.getItems();

        for (Object[] objects : allEqualByProgramName) {
            Student student = (Student) objects[0];
            Payment enrollment = (Payment) objects[1];
            viewAllTms.add(new ViewAllTm(student.getStudentId(),student.getName(),student.getRegistrationDate(),enrollment.getPaymentDate(), (int) enrollment.getAmount(),null));
        }
        viewTbl.setItems(viewAllTms);
        lblStudentCount.setText(String.valueOf(allEqualByProgramName.size()));
    }


    private void setChoiceBoxData(){
        List<courseDTO> program = viewAllBO.getAllCulinaryProgram();
        ObservableList<String> programNames = FXCollections.observableArrayList();

        for (courseDTO programDTO : program){
            programNames.add(programDTO.getProgramName());
        }
        selectPrgramChoiceBox.setItems(programNames);
    }

    @FXML
    void refreshTblOnClickAction(MouseEvent event) {
        if (!selectPrgramChoiceBox.getValue().isEmpty() || selectPrgramChoiceBox.getValue() != null){
            loadTableData(selectPrgramChoiceBox.getValue().trim());
        }
    }


}
