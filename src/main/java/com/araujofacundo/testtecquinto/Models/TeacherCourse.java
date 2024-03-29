package com.araujofacundo.testtecquinto.Models;

import com.araujofacundo.testtecquinto.Models.subclass.Teacher;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class TeacherCourse {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;
    @NonNull
    private Period period;
    private boolean courseWithTeacher = true;
    @ManyToOne
    @JoinColumn (name = "teacher_id")
    private Teacher teacher;
    @ManyToOne
    @JoinColumn (name = "course_id")
    private Course course;
    @OneToMany(mappedBy = "course",fetch = FetchType.LAZY)
    private Set<StudentTeacherCourse> students = new HashSet<>();

    public void addStudent (StudentTeacherCourse studentTeacherCourse){
        students.add(studentTeacherCourse);
        studentTeacherCourse.setCourse(this);
    }


}
