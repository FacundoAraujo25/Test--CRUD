package com.araujofacundo.testtecquinto.Services;

import com.araujofacundo.testtecquinto.DTO.TeacherCourseDTO;
import com.araujofacundo.testtecquinto.Models.TeacherCourse;

import java.util.List;

public interface TeacherCourseService {

    void save(TeacherCourse teacherCourse);

    List<TeacherCourse> getAllUserCourses();

    List<TeacherCourseDTO> getAllUserCoursesDTO(List<TeacherCourse> teacherCours);

    TeacherCourse findById(Long id);

    TeacherCourseDTO getUserCourseDTO(TeacherCourse teacherCourse);



}
