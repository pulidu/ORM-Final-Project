package lk.ijse.orm_final.bo.custom;

import lk.ijse.orm_final.bo.SuperBO;
import lk.ijse.orm_final.dto.PaymentDTO;
import lk.ijse.orm_final.dto.StudentDTO;
import lk.ijse.orm_final.dto.courseDTO;

import java.util.List;

public interface PaymentBO extends SuperBO {

    boolean savePayment(PaymentDTO dto);
    boolean updatePayment(PaymentDTO dto);
    boolean deletePayment(String id);
    PaymentDTO getPayment(String id);
    List<PaymentDTO> getAllPayments();
    String generatePaymentId();

    // Add for loading ComboBoxes
    List<StudentDTO> getAllStudents();
    List<courseDTO> getAllPrograms();
    boolean updateStatus(String paymentId, String newStatus) throws Exception;


}