package lk.ijse.orm_final.bo.custom.impl;

import lk.ijse.orm_final.bo.custom.ViewAllBO;
import lk.ijse.orm_final.dao.DAOFactory;
import lk.ijse.orm_final.dao.custom.CourseDAO;
import lk.ijse.orm_final.dao.custom.QueryDAO;
import lk.ijse.orm_final.dto.courseDTO;
import lk.ijse.orm_final.entity.course;

import java.util.ArrayList;
import java.util.List;


import lk.ijse.orm_final.bo.custom.ViewAllBO;

public class ViewAllBOImpl implements ViewAllBO {


    CourseDAO culinaryProgramDAO = (CourseDAO) DAOFactory.getDAO(DAOFactory.DAOType.PROGRAM);
    QueryDAO queryDAO = (QueryDAO) DAOFactory.getDAO(DAOFactory.DAOType.QUERY);

    @Override
    public List<courseDTO> getAllCulinaryProgram() {
        List<course> allCulinaryProgram = culinaryProgramDAO.getAllCulinaryProgram();
        List<courseDTO> allCulinaryProgramDTO = new ArrayList<>();

        for (course culinaryProgram : allCulinaryProgram) {
            allCulinaryProgramDTO.add(new courseDTO(
                    culinaryProgram.getProgramId(),
                    culinaryProgram.getProgramName(),
                    culinaryProgram.getDuration(),
                    culinaryProgram.getFee(),
                    null
            ));
        }

        return allCulinaryProgramDTO;
    }
    @Override
    public List<Object[]> getAllEqualByProgramName(String programName){
        return queryDAO.getAllEqualByProgramName(programName);
    }
}