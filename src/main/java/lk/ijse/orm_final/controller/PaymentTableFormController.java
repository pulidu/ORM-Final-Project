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
import javafx.stage.Stage;
import lk.ijse.orm_final.bo.BOFactory;
import lk.ijse.orm_final.bo.custom.PaymentBO;
import lk.ijse.orm_final.dto.PaymentDTO;
import lk.ijse.orm_final.tdm.PaymentTM;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



public class PaymentTableFormController {

    public TextField txtStuID;
    public TextField txCouID;

    @FXML
    private TableView<PaymentTM> tblPayments;

    @FXML
    private TableColumn<PaymentTM, String> colPaymentId;

    @FXML
    private TableColumn<PaymentTM, String> colStudent;

    @FXML
    private TableColumn<PaymentTM, String> colProgram;

    @FXML
    private TableColumn<PaymentTM, Double> colAmount;

    @FXML
    private TableColumn<PaymentTM, String> colDate;

    @FXML
    private TableColumn<PaymentTM, String> colStatus;

    private final ObservableList<PaymentTM> paymentList = FXCollections.observableArrayList();
    private final PaymentBO paymentBO = (PaymentBO) BOFactory.getBO(BOFactory.BOType.PAYMENT);

    @FXML
    public void initialize() {
        setupTableColumns();
        loadTableData();


    }

    private void loadTableData(){
        ArrayList<PaymentDTO> allPayment = (ArrayList<PaymentDTO>) paymentBO.getAllPayments();
        ObservableList<PaymentTM>  tableList = FXCollections.observableArrayList();

        if (allPayment != null) {
            for (PaymentDTO paymentDTO : allPayment) {
                tableList.add(new PaymentTM(
                        paymentDTO.getPaymentId(),
                        paymentDTO.getStudentId(),
                        paymentDTO.getProgramId(),
                        paymentDTO.getAmount(),
                        paymentDTO.getPaymentDate(),
                        paymentDTO.getStatus()
                ));
            }
        }
        tblPayments.setItems(tableList);
    }

    // 🔍 Real-time search logic (by StudentID & ProgramID)
    private void searchPayments() {
        String stuId = txtStuID.getText().trim();
        String couId = txCouID.getText().trim();

        if (stuId.isEmpty() && couId.isEmpty()) {
            // Both fields empty → show all
            tblPayments.setItems(paymentList);
            return;
        }

        ObservableList<PaymentTM> filteredList = FXCollections.observableArrayList();

        for (PaymentTM payment : paymentList) {
            boolean match = true;

            if (!stuId.isEmpty() && !payment.getStudentId().toLowerCase().contains(stuId.toLowerCase())) {
                match = false;
            }
            if (!couId.isEmpty() && !payment.getProgramId().toLowerCase().contains(couId.toLowerCase())) {
                match = false;
            }

            if (match) {
                filteredList.add(payment);
            }
        }

        tblPayments.setItems(filteredList);
    }

    @FXML
    void btnAddPayement(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/PayementForm.fxml"));
            Parent root = fxmlLoader.load();

            Stage stage = new Stage();
            stage.setTitle("New Window");
            stage.setScene(new Scene(root));
            stage.show(); // or stage.showAndWait() if you want it modal
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void setupTableColumns(){
        colPaymentId.setCellValueFactory(new PropertyValueFactory<>("paymentId"));
        colStudent.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        colProgram.setCellValueFactory(new PropertyValueFactory<>("programId"));
        colAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

    }
}
