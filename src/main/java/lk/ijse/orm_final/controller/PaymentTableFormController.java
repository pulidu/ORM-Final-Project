package lk.ijse.orm_final.controller;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import lk.ijse.orm_final.bo.BOFactory;
import lk.ijse.orm_final.bo.custom.PaymentBO;
import lk.ijse.orm_final.dto.PaymentDTO;
import lk.ijse.orm_final.tdm.PaymentTM;

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

    }

    private void loadPayments(String s) {
        paymentList.clear();
        try {
            List<PaymentDTO> dtos = paymentBO.getAllPayments();
            for (PaymentDTO dto : dtos) {
                paymentList.add(new PaymentTM(
                        dto.getPaymentId(),
                        dto.getStudentId(),   // ✅ Student ID
                        dto.getProgramId(),   // ✅ Program ID
                        dto.getAmount(),
                        dto.getPaymentDate(),
                        dto.getStatus()
                ));
            }
            tblPayments.setItems(paymentList);
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to load payments!").show();
        }
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
        loadPayments("/PayementForm.fxml" );
    }
}
