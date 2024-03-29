package com.araujofacundo.testtecquinto.Models.subclass;

import com.araujofacundo.testtecquinto.Models.TeacherCourse;
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
public class Teacher extends User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;
    private boolean activeTeacher=true,hasAnyCourse=false;
    @OneToMany(mappedBy = "teacher",fetch = FetchType.LAZY)
    private Set<TeacherCourse> courses = new HashSet<>();

    public Teacher(@NonNull String firstName, @NonNull String lastName, @NonNull String email, @NonNull String password) {
        super(firstName, lastName, email, password);
    }

    public void asignCourse(TeacherCourse teacherCourse){
        teacherCourse.setTeacher(this);
        courses.add(teacherCourse);
    }

}
