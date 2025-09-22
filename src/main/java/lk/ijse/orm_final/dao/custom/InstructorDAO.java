package lk.ijse.orm_final.dao.custom;

import lk.ijse.orm_final.dao.SuperDAO;
import lk.ijse.orm_final.entity.Instructor;

import java.util.List;

public interface InstructorDAO extends SuperDAO {
    boolean save(Instructor instructor);

    boolean update(Instructor instructor);

    boolean delete(String id);

    Instructor search(String id);

    List<Instructor> getAll();

    String generateNewId();

    int count();
}
