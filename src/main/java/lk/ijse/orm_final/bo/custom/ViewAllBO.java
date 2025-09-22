package lk.ijse.orm_final.bo.custom;

import lk.ijse.orm_final.bo.SuperBO;
import lk.ijse.orm_final.dto.courseDTO;

import java.util.List;

public interface ViewAllBO extends SuperBO {


    List<courseDTO> getAllCulinaryProgram();
    List<Object[]> getAllEqualByProgramName(String programName);

}
