package com.araujofacundo.testtecquinto.DTO;

import com.araujofacundo.testtecquinto.Models.StudentTeacherCourse;
import com.araujofacundo.testtecquinto.Models.subclass.Student;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.stream.Collectors;

@NoArgsConstructor
@Getter
public class StudentDTO {

    private long studentId;
    private String name, email, password;
    private Set<StudentTeacherCourseDTO> courses;
    private boolean activeStudent,hasAnyCourse;

    public StudentDTO(Student student) {
        this.studentId = student.getId();
        this.name = student.getFullName();
        this.email = student.getEmail();
        this.password = student.getPassword();
        this.activeStudent = student.isActiveStudent();
        this.courses = student.getCourses().stream().filter(StudentTeacherCourse::isStudentWithCourse).map(course -> new StudentTeacherCourseDTO(course)).collect(Collectors.toSet());
        this.hasAnyCourse = student.isHasAnyCourse();
    }
}
