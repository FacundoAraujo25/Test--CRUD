package com.araujofacundo.testtecquinto.DTO;

import com.araujofacundo.testtecquinto.Models.StudentTeacherCourse;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
public class StudentTeacherCourseDTO {

    private LocalDateTime registeredDate;
    private long idStudentTeacherCourse, idStudent, idTeacherCourse;
    private String courseName;
    private boolean courseWithStudent;


    public StudentTeacherCourseDTO(StudentTeacherCourse studentTeacherCourse){
        this.idStudentTeacherCourse = studentTeacherCourse.getId();
        this.idStudent= studentTeacherCourse.getStudent().getId();
        this.idTeacherCourse = studentTeacherCourse.getCourse().getId();
        this.courseName = studentTeacherCourse.getCourse().getCourse().getName();
        this.courseWithStudent = studentTeacherCourse.isStudentWithCourse();
        this.registeredDate = studentTeacherCourse.getRegisteredDate();
    }

}
