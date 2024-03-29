package com.araujofacundo.testtecquinto.Models.subclass;


import com.araujofacundo.testtecquinto.Models.StudentTeacherCourse;
import com.araujofacundo.testtecquinto.Models.supclass.User;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Student extends User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;
    private boolean activeStudent=true,hasAnyCourse=false;
    @OneToMany(mappedBy = "student",fetch = FetchType.LAZY)
    private Set<StudentTeacherCourse> courses = new HashSet<>();

    public Student(@NonNull String firstName, @NonNull String lastName, @NonNull String email, @NonNull String password) {
        super(firstName, lastName, email, password);
    }

    public void addCourse(StudentTeacherCourse studentTeacherCourse){
        studentTeacherCourse.setStudent(this);
        courses.add(studentTeacherCourse);
    }
}
