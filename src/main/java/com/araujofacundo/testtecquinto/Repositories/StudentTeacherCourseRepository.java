package com.araujofacundo.testtecquinto.Repositories;

import com.araujofacundo.testtecquinto.Models.StudentTeacherCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentTeacherCourseRepository extends JpaRepository<StudentTeacherCourse, Long> {
}
