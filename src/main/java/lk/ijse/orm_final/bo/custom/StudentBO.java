package lk.ijse.orm_final.bo.custom;

import lk.ijse.orm_final.bo.SuperBO;
import lk.ijse.orm_final.dto.StudentDTO;

import java.util.List;

public interface StudentBO extends SuperBO {


    void saveStudent(StudentDTO dto);
    void updateStudent(StudentDTO dto);
    void deleteStudent(StudentDTO dto);
    StudentDTO getStudent(String studentId);
    List<StudentDTO> getAllStudent();
    String generateNewId();


}
