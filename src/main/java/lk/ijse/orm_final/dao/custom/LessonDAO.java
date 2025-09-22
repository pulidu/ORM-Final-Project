package lk.ijse.orm_final.dao.custom;

import lk.ijse.orm_final.dao.SuperDAO;
import lk.ijse.orm_final.entity.Lesson;

import java.util.List;

public interface LessonDAO extends SuperDAO {
    void saveLesson(Lesson lesson);

    void updateLesson(Lesson lesson);

    void deleteLesson(Lesson lesson);

    List<Lesson> getAllLesson();

    Lesson getLesson(String lessonId);

    String getLastLessonId();
}
