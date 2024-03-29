package com.araujofacundo.testtecquinto.Services.Implement;

import com.araujofacundo.testtecquinto.DTO.TeacherDTO;
import com.araujofacundo.testtecquinto.Models.subclass.Teacher;
import com.araujofacundo.testtecquinto.Repositories.TeacherRepository;
import com.araujofacundo.testtecquinto.Services.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeacherServiceImplement implements TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    @Override
    public void save(Teacher teacher) {
        teacherRepository.save(teacher);
    }

    @Override
    public List<Teacher> findAllTeachers() {
        return teacherRepository.findAll();
    }

    @Override
    public List<TeacherDTO> getAllTeachersDTO(List<Teacher> teachers) {
        return teachers.stream().map(teacher -> new TeacherDTO(teacher)).collect(Collectors.toList());
    }

    @Override
    public Teacher findById(Long id) {
        return teacherRepository.findById(id).orElse(null);
    }

    @Override
    public Teacher findByEmail(String email) {
        return teacherRepository.findByEmail(email);
    }

    @Override
    public TeacherDTO getTeacherDTO(Teacher teacher) {
        return new TeacherDTO(teacher);
    }
}
