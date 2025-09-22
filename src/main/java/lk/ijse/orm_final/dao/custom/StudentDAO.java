package lk.ijse.orm_final.dao.custom;

import lk.ijse.orm_final.dao.SuperDAO;
import lk.ijse.orm_final.entity.Student;

import java.util.List;

public interface StudentDAO extends SuperDAO {
    void saveStudent(Student student);

    void deleteStudent(Student student);

    void updateStudent(Student student);

    List<Student> getAllStudent();

    Student getStudent(String studentId);

    Long getStudentCount();

    Student findById(String id);

    List<Student> findAll();

    String generateNewId();
}