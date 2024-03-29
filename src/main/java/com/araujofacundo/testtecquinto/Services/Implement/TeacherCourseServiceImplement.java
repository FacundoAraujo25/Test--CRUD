package com.araujofacundo.testtecquinto.Services.Implement;

import com.araujofacundo.testtecquinto.DTO.TeacherCourseDTO;
import com.araujofacundo.testtecquinto.Models.TeacherCourse;
import com.araujofacundo.testtecquinto.Repositories.UserCourseRepository;
import com.araujofacundo.testtecquinto.Services.TeacherCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeacherCourseServiceImplement implements TeacherCourseService {

    @Autowired
    private UserCourseRepository userCourseRepository;

    @Override
    public void save(TeacherCourse teacherCourse) {
        userCourseRepository.save(teacherCourse);
    }

    @Override
    public List<TeacherCourse> getAllUserCourses() {
        return userCourseRepository.findAll();
    }

    @Override
    public List<TeacherCourseDTO> getAllUserCoursesDTO(List<TeacherCourse> teacherCours) {
        return teacherCours.stream().map(userCourse -> new TeacherCourseDTO(userCourse)).collect(Collectors.toList());
    }

    @Override
    public TeacherCourse findById(Long id) {
        return userCourseRepository.findById(id).orElse(null);
    }

    @Override
    public TeacherCourseDTO getUserCourseDTO(TeacherCourse teacherCourse) {
        return new TeacherCourseDTO(teacherCourse);
    }
}
