package lk.ijse.orm_final.dao.custom;


import lk.ijse.orm_final.dao.SuperDAO;
import lk.ijse.orm_final.dto.courseDTO;
import lk.ijse.orm_final.entity.course;

import java.util.List;

public interface CourseDAO  extends SuperDAO {
    void saveCulinaryProgram(course culinaryProgram);
    void deleteCulinaryProgram(course culinaryProgram);
    void updateCulinaryProgram(course culinaryProgram);
    List<course> getAllCulinaryProgram();
    courseDTO getProgramsCheckName(String programName);
    course getCulinaryProgram(String programId);
    Long getCulinaryProgramCount();

    String generateProgramId(); // new
    course findById(String id);
    List<course> findAll();
}