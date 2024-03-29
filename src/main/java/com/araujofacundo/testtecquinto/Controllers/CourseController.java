package com.araujofacundo.testtecquinto.Controllers;

import com.araujofacundo.testtecquinto.DTO.CourseDTO;
import com.araujofacundo.testtecquinto.Models.Course;
import com.araujofacundo.testtecquinto.Services.CourseService;
import com.araujofacundo.testtecquinto.Services.TeacherCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private TeacherCourseService teacherCourseService;

    @GetMapping("/courses")
    public List<CourseDTO> getAllCourses(){
        List<Course> courses = courseService.findAllCourses();
        return courseService.getAllCoursesDTO(courses);
    }

    @GetMapping("/courses/{id}")
    public CourseDTO getCourse(@PathVariable Long id){
        Course course = courseService.findById(id);
        return courseService.getCourseDTO(course);
    }

    @PostMapping("/courses")
    public ResponseEntity<Object> createCourse(@RequestParam String name,
                                               @RequestParam ("startDate")@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                               @RequestParam ("finishDate")@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate finishDate){

        if(name.isBlank()){
            return new ResponseEntity<>("Missing course name", HttpStatus.FORBIDDEN);
        }

        if(startDate.isBefore(LocalDate.now())){
            return new ResponseEntity<>("Invalid start date", HttpStatus.FORBIDDEN);
        }
        if(finishDate.isBefore(startDate)){
            return new ResponseEntity<>("Invalid finish date", HttpStatus.FORBIDDEN);
        }

        Course course = new Course(name, startDate, finishDate);
        courseService.save(course);

        return new ResponseEntity<>(HttpStatus.CREATED);

    }

    @PatchMapping("/courses/{id}")
    public ResponseEntity<Object> editCourse (@PathVariable Long courseId, @RequestParam String name,
                                              @RequestParam ("startDate")@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                              @RequestParam ("finishDate")@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate finishDate){

        Course course = courseService.findById(courseId);

        if(course == null){
            return new ResponseEntity<>("Course does not exist", HttpStatus.FORBIDDEN);
        }

        course.setName(name);
        course.setStartDate(startDate);
        course.setFinishDate(finishDate);
        courseService.save(course);

        return new ResponseEntity<>("Course succesfully updated",HttpStatus.ACCEPTED);

    }

    @DeleteMapping("/courses/{id}")
    public ResponseEntity<Object> disableCourse (@PathVariable Long id){

        Course course = courseService.findById(id);

        if(course == null){
            return new ResponseEntity<>("Course does not exist", HttpStatus.FORBIDDEN);
        }

        course.setActiveCourse(false);
        courseService.save(course);

        return new ResponseEntity<>("Course succesfully deleted",HttpStatus.ACCEPTED);

    }


}
