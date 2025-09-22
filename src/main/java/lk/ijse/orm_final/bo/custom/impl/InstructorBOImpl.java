package lk.ijse.orm_final.bo.custom.impl;

import lk.ijse.orm_final.bo.custom.InstructorBO;
import lk.ijse.orm_final.dao.DAOFactory;
import lk.ijse.orm_final.dao.custom.InstructorDAO;
import lk.ijse.orm_final.dto.InstructorDTO;
import lk.ijse.orm_final.entity.Instructor;

import java.util.ArrayList;
import java.util.List;


import lk.ijse.orm_final.bo.custom.InstructorBO;

public class InstructorBOImpl implements InstructorBO {

    InstructorDAO instructorDAO = (InstructorDAO) DAOFactory.getDAO(DAOFactory.DAOType.INSTRUCTOR);

    @Override
    public boolean saveInstructor(InstructorDTO dto) {
        return instructorDAO.save(new Instructor(dto.getInstructorId(), dto.getName(), dto.getSpecialization(), null, null));
    }

    @Override
    public boolean updateInstructor(InstructorDTO dto) {
        return instructorDAO.update(new Instructor(dto.getInstructorId(), dto.getName(), dto.getSpecialization(), null, null));
    }

    @Override
    public boolean deleteInstructor(String id) {
        return instructorDAO.delete(id);
    }

    @Override
    public InstructorDTO searchInstructor(String id) {
        Instructor i = instructorDAO.search(id);
        if (i != null) return new InstructorDTO(i.getInstructorId(), i.getName(), i.getSpecialization());
        return null;
    }

    @Override
    public List<InstructorDTO> getAllInstructors() {
        List<Instructor> list = instructorDAO.getAll();
        List<InstructorDTO> dtoList = new ArrayList<>();
        for (Instructor i : list) dtoList.add(new InstructorDTO(i.getInstructorId(), i.getName(), i.getSpecialization()));
        return dtoList;
    }

    @Override
    public String generateNewId() {
        return instructorDAO.generateNewId();
    }

}