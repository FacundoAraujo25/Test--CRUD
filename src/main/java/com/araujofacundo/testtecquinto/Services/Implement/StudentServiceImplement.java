package com.araujofacundo.testtecquinto.Services.Implement;

import com.araujofacundo.testtecquinto.DTO.StudentDTO;
import com.araujofacundo.testtecquinto.Models.subclass.Student;
import com.araujofacundo.testtecquinto.Repositories.StudentRepository;
import com.araujofacundo.testtecquinto.Services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImplement implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public void save(Student student) {
        studentRepository.save(student);
    }

    @Override
    public List<Student> findAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public List<StudentDTO> getAllStudentsDTO(List<Student> students) {
        return students.stream().map(student -> new StudentDTO(student)).collect(Collectors.toList());
    }

    @Override
    public Student findById(Long id) {
        return studentRepository.findById(id).orElse(null);
    }

    @Override
    public Student findByEmail(String email) {
        return studentRepository.findByEmail(email);
    }

    @Override
    public StudentDTO getStudentDTO(Student student) {
        return new StudentDTO(student);
    }
}
