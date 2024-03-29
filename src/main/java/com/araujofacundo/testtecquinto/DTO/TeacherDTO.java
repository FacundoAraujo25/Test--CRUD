package com.araujofacundo.testtecquinto.DTO;

import com.araujofacundo.testtecquinto.Models.TeacherCourse;
import com.araujofacundo.testtecquinto.Models.subclass.Teacher;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.stream.Collectors;

@NoArgsConstructor
@Getter
public class TeacherDTO {

    private long teacherId;
    private String name, email, password;
    private Set<TeacherCourseDTO> courses;
    private boolean activeTeacher,hasAnyCourse;
    public TeacherDTO(Teacher teacher){
        this.teacherId = teacher.getId();
        this.name = teacher.getFullName();
        this.email = teacher.getEmail();
        this.password = teacher.getPassword();
        this.activeTeacher = teacher.isActiveTeacher();
        this.courses = teacher.getCourses().stream().filter(TeacherCourse::isCourseWithTeacher).map(teacherCourse -> new TeacherCourseDTO(teacherCourse)).collect(Collectors.toSet());
        this.hasAnyCourse = teacher.isHasAnyCourse();
    }

}
