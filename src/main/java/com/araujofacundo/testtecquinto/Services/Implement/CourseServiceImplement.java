package com.araujofacundo.testtecquinto.Services.Implement;

import com.araujofacundo.testtecquinto.DTO.CourseDTO;
import com.araujofacundo.testtecquinto.Models.Course;
import com.araujofacundo.testtecquinto.Repositories.CourseRepository;
import com.araujofacundo.testtecquinto.Services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseServiceImplement implements CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public void save(Course course) {
        courseRepository.save(course);
    }

    @Override
    public List<Course> findAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    public List<CourseDTO> getAllCoursesDTO(List<Course> courses) {
        return courses.stream().map(course -> new CourseDTO(course)).collect(Collectors.toList());
    }

    @Override
    public Course findById(Long id) {
        return courseRepository.findById(id).orElse(null);
    }

    @Override
    public Course findByName(String name) {
        return courseRepository.findByName(name);
    }

    @Override
    public CourseDTO getCourseDTO(Course course) {
        return new CourseDTO(course);
    }
}
