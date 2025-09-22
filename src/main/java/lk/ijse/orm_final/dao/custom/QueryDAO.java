package lk.ijse.orm_final.dao.custom;


import lk.ijse.orm_final.dao.SuperDAO;
import lk.ijse.orm_final.entity.Student;

import java.util.List;

public interface QueryDAO extends SuperDAO {
    List<Student> getAllProgramsStudent();

    List<Object[]> getAllEqualByProgramName(String programName);
}
