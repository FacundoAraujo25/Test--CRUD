package com.araujofacundo.testtecquinto.Services.Implement;

import com.araujofacundo.testtecquinto.DTO.StudentTeacherCourseDTO;
import com.araujofacundo.testtecquinto.Models.StudentTeacherCourse;
import com.araujofacundo.testtecquinto.Repositories.StudentTeacherCourseRepository;
import com.araujofacundo.testtecquinto.Services.StudentTeacherCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentTeacherCourseServiceImplement implements StudentTeacherCourseService {

    @Autowired
    private StudentTeacherCourseRepository studentTeacherCourseRepository;

    @Override
    public void save(StudentTeacherCourse studentTeacherCourse) {
        studentTeacherCourseRepository.save(studentTeacherCourse);
    }

    @Override
    public List<StudentTeacherCourse> findAll() {
        return studentTeacherCourseRepository.findAll();
    }

    @Override
    public List<StudentTeacherCourseDTO> getAllDTO(List<StudentTeacherCourse> courses) {
        return courses.stream().map(studentTeacherCourse -> new StudentTeacherCourseDTO(studentTeacherCourse)).collect(Collectors.toList());
    }

    @Override
    public StudentTeacherCourse findById(Long id) {
        return studentTeacherCourseRepository.findById(id).orElse(null);
    }

    @Override
    public StudentTeacherCourseDTO getStudentDTO(StudentTeacherCourse studentTeacherCourse) {
        return new StudentTeacherCourseDTO(studentTeacherCourse);
    }
}
