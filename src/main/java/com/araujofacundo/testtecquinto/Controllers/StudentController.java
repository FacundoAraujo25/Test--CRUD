package com.araujofacundo.testtecquinto.Controllers;

import com.araujofacundo.testtecquinto.DTO.StudentDTO;
import com.araujofacundo.testtecquinto.DTO.TeacherDTO;
import com.araujofacundo.testtecquinto.Models.Role;
import com.araujofacundo.testtecquinto.Models.StudentTeacherCourse;
import com.araujofacundo.testtecquinto.Models.TeacherCourse;
import com.araujofacundo.testtecquinto.Models.subclass.Student;
import com.araujofacundo.testtecquinto.Models.subclass.Teacher;
import com.araujofacundo.testtecquinto.Services.StudentService;
import com.araujofacundo.testtecquinto.Services.StudentTeacherCourseService;
import com.araujofacundo.testtecquinto.Services.TeacherCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class StudentController {

    @Autowired
    private StudentService studentService;
    @Autowired
    private StudentTeacherCourseService studentTeacherCourseService;
    @Autowired
    private TeacherCourseService teacherCourseService;

    @GetMapping("/students")
    public List<StudentDTO> getAllStudents(){
        List<Student> students = studentService.findAllStudents();
        return studentService.getAllStudentsDTO(students);
    }

    @GetMapping("/students/{id}")
    public StudentDTO getStudent(@PathVariable Long id){
        Student student = studentService.findById(id);
        return studentService.getStudentDTO(student);
    }

    @PostMapping("/students")
    public ResponseEntity<Object> createStudent(@RequestParam String firstName, @RequestParam String lastName,
                                             @RequestParam String email, @RequestParam String password){

        if(firstName.isBlank()){
            return new ResponseEntity<>("Missing first name", HttpStatus.FORBIDDEN);
        }

        if(lastName.isBlank()){
            return new ResponseEntity<>("Missing last name", HttpStatus.FORBIDDEN);
        }

        if(email.isBlank()){
            return new ResponseEntity<>("Missing email", HttpStatus.FORBIDDEN);
        }

        if(studentService.findByEmail(email) != null){
            return new ResponseEntity<>("There is a student using this email", HttpStatus.FORBIDDEN);
        }

        if(password.isBlank()){
            return new ResponseEntity<>("Missing password", HttpStatus.FORBIDDEN);
        }

        Student student = new Student(firstName, lastName, email, password, Role.STUDENT);
        studentService.save(student);

        return new ResponseEntity<>(HttpStatus.CREATED);

    }

    @PostMapping("/students/{id}/courses")
    public ResponseEntity<Object> registerStudentToCourse(@PathVariable Long id, @RequestParam Long courseId){

        Student student = studentService.findById(id);
        TeacherCourse teacherCourse = teacherCourseService.findById(courseId);

        if(student == null){
            return new ResponseEntity<>("Student not found", HttpStatus.FORBIDDEN);
        }

        if(teacherCourse == null){
            return new ResponseEntity<>("Course not found", HttpStatus.FORBIDDEN);
        }

        StudentTeacherCourse studentTeacherCourse = new StudentTeacherCourse(LocalDateTime.now());
        studentTeacherCourseService.save(studentTeacherCourse);

        student.addCourse(studentTeacherCourse);
        student.setHasAnyCourse(true);
        teacherCourse.addStudent(studentTeacherCourse);
        studentTeacherCourseService.save(studentTeacherCourse);
        teacherCourseService.save(teacherCourse);
        studentService.save(student);

        return new ResponseEntity<>("Student succesfully asigned",HttpStatus.ACCEPTED);
    }

    @PatchMapping("/students/{id}/courses")
    public ResponseEntity<Object> quitStudentFromCourse(@PathVariable Long id, @RequestParam Long courseId) {

        Student student = studentService.findById(id);
        StudentTeacherCourse studentTeacherCourse = studentTeacherCourseService.findById(courseId);

        if(student == null){
            return new ResponseEntity<>("Student not found", HttpStatus.FORBIDDEN);
        }

        if(studentTeacherCourse == null){
            return new ResponseEntity<>("Course not found", HttpStatus.FORBIDDEN);
        }

        if(!student.getCourses().contains(studentTeacherCourse) || !studentTeacherCourse.isStudentWithCourse()){
            return new ResponseEntity<>("This student is not from this course", HttpStatus.FORBIDDEN);
        }

        if(studentTeacherCourse.getStudent().getId() != student.getId()){
            return new ResponseEntity<>("This student is not in this course", HttpStatus.FORBIDDEN);
        }


        studentTeacherCourse.setStudentWithCourse(false);
        studentTeacherCourse.setUnregisteredDate(LocalDateTime.now());
        studentTeacherCourseService.save(studentTeacherCourse);

        if(student.getCourses().stream().noneMatch(StudentTeacherCourse::isStudentWithCourse)){
            student.setHasAnyCourse(false);
        }

        studentService.save(student);

        return new ResponseEntity<>("Student succesfully unregistered from the course",HttpStatus.ACCEPTED);

    }

    @DeleteMapping("/students/{id}")
    public ResponseEntity<Object> unregisterStudent(@PathVariable Long id) {

        Student student = studentService.findById(id);

        if(student == null){
            return new ResponseEntity<>("Student not found", HttpStatus.FORBIDDEN);
        }

        student.setActiveStudent(false);
        studentService.save(student);

        return new ResponseEntity<>("Student succesfully unregistered",HttpStatus.ACCEPTED);

    }

}
