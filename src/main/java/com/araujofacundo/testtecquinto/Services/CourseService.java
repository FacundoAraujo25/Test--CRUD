package com.araujofacundo.testtecquinto.Services;

import com.araujofacundo.testtecquinto.DTO.CourseDTO;
import com.araujofacundo.testtecquinto.Models.Course;

import java.util.List;

public interface CourseService {

    void save(Course course);

    List<Course> findAllCourses();

    List<CourseDTO> getAllCoursesDTO(List<Course> courses);

    Course findById(Long id);

    Course findByName(String name);

    CourseDTO getCourseDTO(Course course);

}
