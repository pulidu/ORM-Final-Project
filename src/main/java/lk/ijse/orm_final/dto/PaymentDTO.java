package lk.ijse.orm_final.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class PaymentDTO {
    private String paymentId;
    private String studentId;
    private String programId;
    private double amount;
    private String paymentDate;
    private String status;

}