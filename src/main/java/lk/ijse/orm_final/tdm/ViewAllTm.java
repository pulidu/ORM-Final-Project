package lk.ijse.orm_final.tdm;

import javafx.scene.control.Button;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class ViewAllTm {
    private String studentId;
    private String studentName;
    private Date registerDate;
    private Date installment;
    private int balance;
    private Button payment;
}
