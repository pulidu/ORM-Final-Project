package lk.ijse.orm_final.bo.custom;

import lk.ijse.orm_final.bo.SuperBO;
import lk.ijse.orm_final.dto.StudentDTO;

import java.util.List;

public interface DashboardBO extends SuperBO {

    Long getCulinaryProgramCount();
    Long getStudentCount();
    List<StudentDTO> getAllProgramStudents();
    int getInstructorCount();
}

