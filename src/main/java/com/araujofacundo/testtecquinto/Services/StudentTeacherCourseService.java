package com.araujofacundo.testtecquinto.Services;

import com.araujofacundo.testtecquinto.DTO.StudentTeacherCourseDTO;
import com.araujofacundo.testtecquinto.Models.StudentTeacherCourse;
import java.util.List;

public interface StudentTeacherCourseService {

    void save(StudentTeacherCourse studentTeacherCourse);

    List<StudentTeacherCourse> findAll();

    List<StudentTeacherCourseDTO> getAllDTO(List<StudentTeacherCourse> courses);

    StudentTeacherCourse findById(Long id);

    StudentTeacherCourseDTO getStudentDTO(StudentTeacherCourse studentTeacherCourse);

}
