package com.araujofacundo.testtecquinto.DTO;

import com.araujofacundo.testtecquinto.Models.Period;
import com.araujofacundo.testtecquinto.Models.TeacherCourse;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
public class TeacherCourseDTO {

    private long id;
    private String courseName, teacherName;
    private Period period;
    private boolean courseWithTeacher;

    public TeacherCourseDTO(TeacherCourse teacherCourse) {
        this.id = teacherCourse.getId();
        this.courseName = teacherCourse.getCourse().getName();
        this.period = teacherCourse.getPeriod();
        this.teacherName = teacherCourse.getTeacher().getFullName();
        this.courseWithTeacher = teacherCourse.isCourseWithTeacher();
    }

}
