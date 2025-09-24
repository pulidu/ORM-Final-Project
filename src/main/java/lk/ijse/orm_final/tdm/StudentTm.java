package lk.ijse.orm_final.tdm;

import javafx.scene.control.Button;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class StudentTm extends StudyAllStudentTm {
    private String studentId;
    private String name;
    private String address;
    private Long tel;
    private Date registrationDate;
    private Button program;
}
