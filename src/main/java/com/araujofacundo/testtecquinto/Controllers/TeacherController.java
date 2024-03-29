package com.araujofacundo.testtecquinto.Controllers;

import com.araujofacundo.testtecquinto.DTO.CourseDTO;
import com.araujofacundo.testtecquinto.DTO.TeacherDTO;
import com.araujofacundo.testtecquinto.Models.Course;
import com.araujofacundo.testtecquinto.Models.Period;
import com.araujofacundo.testtecquinto.Models.Role;
import com.araujofacundo.testtecquinto.Models.TeacherCourse;
import com.araujofacundo.testtecquinto.Models.subclass.Student;
import com.araujofacundo.testtecquinto.Models.subclass.Teacher;
import com.araujofacundo.testtecquinto.Services.CourseService;
import com.araujofacundo.testtecquinto.Services.TeacherCourseService;
import com.araujofacundo.testtecquinto.Services.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private TeacherCourseService teacherCourseService;

    @GetMapping("/teachers")
    public List<TeacherDTO> getAllTeachers(){
        List<Teacher> teachers = teacherService.findAllTeachers();
        return teacherService.getAllTeachersDTO(teachers);
    }

    @GetMapping("/teachers/{id}")
    public TeacherDTO getTeacher(@PathVariable Long id){
        Teacher teacher = teacherService.findById(id);
        return teacherService.getTeacherDTO(teacher);
    }

    @PostMapping("/teachers")
    public ResponseEntity<Object> createUser(@RequestParam String firstName,@RequestParam String lastName,
                                             @RequestParam String email,@RequestParam String password){

        if(firstName.isBlank()){
            return new ResponseEntity<>("Missing first name", HttpStatus.FORBIDDEN);
        }

        if(lastName.isBlank()){
            return new ResponseEntity<>("Missing last name", HttpStatus.FORBIDDEN);
        }

        if(email.isBlank()){
            return new ResponseEntity<>("Missing email", HttpStatus.FORBIDDEN);
        }

        if(teacherService.findByEmail(email) != null){
            return new ResponseEntity<>("There is a teacher using this email", HttpStatus.FORBIDDEN);
        }

        if(password.isBlank()){
            return new ResponseEntity<>("Missing password", HttpStatus.FORBIDDEN);
        }

        Teacher teacher = new Teacher(firstName, lastName, email, password, Role.TEACHER);
        teacherService.save(teacher);

        return new ResponseEntity<>(HttpStatus.CREATED);

    }

    @PostMapping("/teachers/{id}/courses")
    public ResponseEntity<Object> registerTeachersToCourse(@PathVariable Long id, @RequestParam Long courseId, @RequestParam Period period){

        Teacher teacher = teacherService.findById(id);
        Course course = courseService.findById(courseId);

        if(teacher == null){
            return new ResponseEntity<>("Teacher not found", HttpStatus.FORBIDDEN);
        }

        if(course == null){
            return new ResponseEntity<>("Course does not exist", HttpStatus.FORBIDDEN);
        }

        TeacherCourse teacherCourse = new TeacherCourse(period);
        teacherCourseService.save(teacherCourse);
        course.addTeacher(teacherCourse);
        teacher.asignCourse(teacherCourse);
        teacher.setHasAnyCourse(true);
        courseService.save(course);
        teacherService.save(teacher);
        teacherCourseService.save(teacherCourse);
        return new ResponseEntity<>("Teacher succesfully asigned",HttpStatus.ACCEPTED);
    }

    @PatchMapping("/teachers/{id}/courses")
    @Transactional
    public ResponseEntity<Object> quitTeacherFromCourse(@PathVariable Long id, @RequestParam Long courseId){

        Teacher teacher = teacherService.findById(id);
        TeacherCourse course = teacherCourseService.findById(courseId);

        if(teacher == null){
            return new ResponseEntity<>("Teacher not found", HttpStatus.FORBIDDEN);
        }

        if(course == null){
            return new ResponseEntity<>("Course does not exist", HttpStatus.FORBIDDEN);
        }

        if(teacher.getCourses().isEmpty()){
            return new ResponseEntity<>("Teacher has not any course", HttpStatus.FORBIDDEN);
        }

        if(course.getTeacher().getId() != teacher.getId()){
            return new ResponseEntity<>("This teacher has not this course", HttpStatus.FORBIDDEN);
        }

        if(!teacher.getCourses().contains(course) || !course.isCourseWithTeacher()){
            return new ResponseEntity<>("Teacher has not this course", HttpStatus.FORBIDDEN);
        }

        course.setCourseWithTeacher(false);
        teacherCourseService.save(course);

        if(teacher.getCourses().stream().noneMatch(TeacherCourse::isCourseWithTeacher)){
            teacher.setHasAnyCourse(false);
        }

        teacherService.save(teacher);

        return new ResponseEntity<>("Teacher succesfully quit from course",HttpStatus.ACCEPTED);

    }

    @DeleteMapping("/teachers/{id}")
    public ResponseEntity<Object> unregisterTeacher(@PathVariable Long id) {

        Teacher teacher = teacherService.findById(id);

        if(teacher == null){
            return new ResponseEntity<>("Teacher not found", HttpStatus.FORBIDDEN);
        }

        if(!teacher.getCourses().isEmpty()){
            return new ResponseEntity<>("Teacher has active courses ", HttpStatus.FORBIDDEN);
        }

        teacher.setActiveTeacher(false);
        teacherService.save(teacher);

        return new ResponseEntity<>("Teacher succesfully unregistered",HttpStatus.ACCEPTED);

    }

}
