package com.araujofacundo.testtecquinto.Repositories;

import com.araujofacundo.testtecquinto.Models.TeacherCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCourseRepository extends JpaRepository<TeacherCourse, Long> {
}
