package lk.ijse.orm_final.bo.custom;

import lk.ijse.orm_final.bo.SuperBO;
import lk.ijse.orm_final.dto.InstructorDTO;

import java.util.List;

public interface InstructorBO extends SuperBO {

    boolean saveInstructor(InstructorDTO dto);
    boolean updateInstructor(InstructorDTO dto);
    boolean deleteInstructor(String id);
    InstructorDTO searchInstructor(String id);
    List<InstructorDTO> getAllInstructors();
    String generateNewId();

}
