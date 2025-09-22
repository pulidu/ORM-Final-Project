package lk.ijse.orm_final.bo.custom.impl;


import lk.ijse.orm_final.bo.custom.CourseBO;
import lk.ijse.orm_final.dao.DAOFactory;
import lk.ijse.orm_final.dao.custom.CourseDAO;
import lk.ijse.orm_final.dto.courseDTO;
import lk.ijse.orm_final.entity.course;

import java.util.ArrayList;
import java.util.List;

public class CourseBOImpl implements CourseBO {

    CourseDAO culinaryProgramDAO = (CourseDAO) DAOFactory.getDAO(DAOFactory.DAOType.PROGRAM);

    @Override
    public void saveCulinaryProgram(courseDTO dto) {
        course program = new course(dto.getProgramId(), dto.getProgramName(), dto.getDuration(), dto.getFee());
        culinaryProgramDAO.saveCulinaryProgram(program);
    }

    @Override
    public void deleteCulinaryProgram(courseDTO dto) {
        course program = new course(dto.getProgramId(), dto.getProgramName(), dto.getDuration(), dto.getFee());
        culinaryProgramDAO.deleteCulinaryProgram(program);
    }

    @Override
    public void updateCulinaryProgram(courseDTO dto) {
        course program = new course(dto.getProgramId(), dto.getProgramName(), dto.getDuration(), dto.getFee());
        culinaryProgramDAO.updateCulinaryProgram(program);
    }

    @Override
    public List<courseDTO> getAllCulinaryProgram() {
        List<course> programs = culinaryProgramDAO.getAllCulinaryProgram();
        List<courseDTO> dtoList = new ArrayList<>();
        for (course p : programs) {
            dtoList.add(new courseDTO(p.getProgramId(), p.getProgramName(), p.getDuration(), p.getFee(), null));
        }
        return dtoList;
    }

    @Override
    public courseDTO getCulinaryProgram(String programId){
        course p = culinaryProgramDAO.getCulinaryProgram(programId);
        if(p == null) return null;
        return new courseDTO(p.getProgramId(), p.getProgramName(), p.getDuration(), p.getFee(), null);
    }

    @Override
    public String generateProgramId() {
        return culinaryProgramDAO.generateProgramId();
    }

}
