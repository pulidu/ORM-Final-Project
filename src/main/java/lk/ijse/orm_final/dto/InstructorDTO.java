package lk.ijse.orm_final.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class InstructorDTO {
    private String instructorId;
    private String Name;
    private String specialization;
}
