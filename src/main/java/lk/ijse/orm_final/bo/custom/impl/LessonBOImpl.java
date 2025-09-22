package lk.ijse.orm_final.bo.custom.impl;

import lk.ijse.orm_final.bo.custom.LessonBO;
import lk.ijse.orm_final.dao.DAOFactory;
import lk.ijse.orm_final.dao.custom.CourseDAO;
import lk.ijse.orm_final.dao.custom.InstructorDAO;
import lk.ijse.orm_final.dao.custom.LessonDAO;
import lk.ijse.orm_final.dao.custom.StudentDAO;
import lk.ijse.orm_final.dto.LessonDTO;
import lk.ijse.orm_final.entity.Lesson;
import lk.ijse.orm_final.entity.Student;
import lk.ijse.orm_final.entity.course;
import lk.ijse.orm_final.entity.Instructor;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import lk.ijse.orm_final.bo.custom.LessonBO;

public class LessonBOImpl implements LessonBO {

    private final LessonDAO lessonDAO = (LessonDAO) DAOFactory.getDAO(DAOFactory.DAOType.LESSON);
    private final StudentDAO studentDAO = (StudentDAO) DAOFactory.getDAO(DAOFactory.DAOType.STUDENT);
    private final CourseDAO courseDAO = (CourseDAO) DAOFactory.getDAO(DAOFactory.DAOType.COURSE);
    private final InstructorDAO instructorDAO = (InstructorDAO) DAOFactory.getDAO(DAOFactory.DAOType.INSTRUCTOR);

    @Override
    public String generateNextLessonId() {
        String lastId = lessonDAO.getLastLessonId();
        if (lastId != null) {
            int id = Integer.parseInt(lastId.substring(1)) + 1;
            return String.format("L%03d", id);
        } else {
            return "L001";
        }
    }

    @Override
    public void saveLesson(LessonDTO dto) {
        Lesson lesson = new Lesson();
        lesson.setLessonId(dto.getLessonId());
        lesson.setStudent(new Student(dto.getStudentId()));
        lesson.setCourse(new course(dto.getCourseId()));
        lesson.setInstructor(new Instructor(dto.getInstructorId()));
        lesson.setLessonDate(Timestamp.valueOf(LocalDateTime.of(dto.getLessonDate(), LocalTime.parse(dto.getLessonTime()))));
        lesson.setDuration(dto.getDuration());

        lessonDAO.saveLesson(lesson);
    }

    @Override
    public void updateLesson(LessonDTO dto) {
        Lesson lesson = new Lesson();
        lesson.setLessonId(dto.getLessonId());
        lesson.setStudent(new Student(dto.getStudentId()));
        lesson.setCourse(new course(dto.getCourseId()));
        lesson.setInstructor(new Instructor(dto.getInstructorId()));
        lesson.setLessonDate(Timestamp.valueOf(LocalDateTime.of(dto.getLessonDate(), LocalTime.parse(dto.getLessonTime()))));
        lesson.setDuration(dto.getDuration());

        lessonDAO.updateLesson(lesson);
    }

    @Override
    public void deleteLesson(LessonDTO dto) {
        Lesson lesson = new Lesson();
        lesson.setLessonId(dto.getLessonId());
        lessonDAO.deleteLesson(lesson);
    }

    @Override
    public LessonDTO getLesson(String lessonId) {
        Lesson lesson = lessonDAO.getLesson(lessonId);
        return new LessonDTO(
                lesson.getLessonId(),
                lesson.getStudent().getStudentId(),
                lesson.getCourse().getProgramId(),
                lesson.getInstructor().getInstructorId(),
                lesson.getLessonDate().toLocalDateTime().toLocalDate(),
                lesson.getLessonDate().toLocalDateTime().toLocalTime().toString(),
                lesson.getDuration()
        );
    }

    @Override
    public List<LessonDTO> getAllLesson() {
        List<Lesson> all = lessonDAO.getAllLesson();
        List<LessonDTO> dtos = new ArrayList<>();
        for (Lesson lesson : all) {
            dtos.add(new LessonDTO(
                    lesson.getLessonId(),
                    lesson.getStudent().getStudentId(),
                    lesson.getCourse().getProgramId(),
                    lesson.getInstructor().getInstructorId(),
                    lesson.getLessonDate().toLocalDateTime().toLocalDate(),
                    lesson.getLessonDate().toLocalDateTime().toLocalTime().toString(),
                    lesson.getDuration()
            ));
        }
        return dtos;
    }

}