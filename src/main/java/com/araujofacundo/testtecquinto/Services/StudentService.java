package com.araujofacundo.testtecquinto.Services;

import com.araujofacundo.testtecquinto.DTO.StudentDTO;
import com.araujofacundo.testtecquinto.Models.subclass.Student;
import java.util.List;

public interface StudentService {

    void save(Student student);

    List<Student> findAllStudents();

    List<StudentDTO> getAllStudentsDTO(List<Student> students);

    Student findById(Long id);

    Student findByEmail(String email);

    StudentDTO getStudentDTO(Student student);


}
