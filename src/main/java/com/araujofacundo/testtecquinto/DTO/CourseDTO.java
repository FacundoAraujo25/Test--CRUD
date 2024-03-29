package com.araujofacundo.testtecquinto.DTO;

import com.araujofacundo.testtecquinto.Models.Course;
import com.araujofacundo.testtecquinto.Models.Period;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

@NoArgsConstructor
@Getter
public class CourseDTO {

    private long courseId;
    private String name;
    private LocalDate startDate, finishDate;
    private boolean activeCourse;
    private Set<TeacherCourseDTO> teachers;

    public CourseDTO(Course course) {
        this.courseId = course.getId();
        this.name = course.getName();
        this.startDate = course.getStartDate();
        this.finishDate = course.getFinishDate();
        this.activeCourse = course.isActiveCourse();
        this.teachers = course.getTeachers().stream().map(teacherCourse -> new TeacherCourseDTO(teacherCourse)).collect(Collectors.toSet());
    }
}
