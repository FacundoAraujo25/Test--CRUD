package com.araujofacundo.testtecquinto.Models;

import com.araujofacundo.testtecquinto.Models.subclass.Student;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class StudentTeacherCourse {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;
    @NonNull
    private LocalDateTime registeredDate;
    private LocalDateTime unregisteredDate;
    private boolean studentWithCourse = true;
    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private TeacherCourse course;

}
