package lk.ijse.orm_final.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class StudentDTO {
    private String studentId;
    private String name;
    private String address;
    private Long tel;
    private Date registrationDate;


}
