package com.araujofacundo.testtecquinto.Services;

import com.araujofacundo.testtecquinto.DTO.TeacherDTO;
import com.araujofacundo.testtecquinto.Models.subclass.Teacher;
import java.util.List;

public interface TeacherService {

    void save(Teacher teacher);

    List<Teacher> findAllTeachers();

    List<TeacherDTO> getAllTeachersDTO(List<Teacher> teachers);

    Teacher findById(Long id);

    Teacher findByEmail(String email);

    TeacherDTO getTeacherDTO(Teacher teacher);

}
