package com.araujofacundo.testtecquinto.Models;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;


@Entity
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;
    @NonNull
    private String name;
    @NonNull
    private LocalDate startDate, finishDate;
    private boolean activeCourse = true;
    //relacion con courses
    @OneToMany(mappedBy = "course",fetch = FetchType.LAZY)
    private Set<TeacherCourse> teachers = new HashSet<>();



    public void addTeacher (TeacherCourse teacher){
        teacher.setCourse(this);
        teachers.add(teacher);
    }

}
