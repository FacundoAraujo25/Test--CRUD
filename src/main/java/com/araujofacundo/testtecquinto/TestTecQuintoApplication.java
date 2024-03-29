package com.araujofacundo.testtecquinto;

import com.araujofacundo.testtecquinto.Models.Course;
import com.araujofacundo.testtecquinto.Models.Period;
import com.araujofacundo.testtecquinto.Models.StudentTeacherCourse;
import com.araujofacundo.testtecquinto.Models.TeacherCourse;
import com.araujofacundo.testtecquinto.Models.subclass.Admin;
import com.araujofacundo.testtecquinto.Models.subclass.Student;
import com.araujofacundo.testtecquinto.Models.subclass.Teacher;
import com.araujofacundo.testtecquinto.Services.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.time.LocalDateTime;

@SpringBootApplication
public class TestTecQuintoApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestTecQuintoApplication.class, args);
	}

	@Bean
	public CommandLineRunner initData(CourseService courseService, TeacherService teacherService, StudentService studentService, TeacherCourseService teacherCourseService, StudentTeacherCourseService studentTeacherCourseService, AdminService adminService, PasswordEncoder passwordEncoder){
		return (args -> {

			Teacher teacher1 = new Teacher("Gabriela","Rossi","gabiRossi@teachersFI.com", passwordEncoder.encode("gabi123"));
			Teacher teacher2 = new Teacher("Facundo", "Araujo","facu@teachersFI.com", passwordEncoder.encode("facu123"));
			Teacher teacher3 = new Teacher("Jorge", "Lopez","jorge@gmail.com", passwordEncoder.encode("jorge123"));

			Student student1 = new Student("Agustin","Araujo","agu@gmail.com", passwordEncoder.encode("agu123"));
			Student student2 = new Student("Yuli","Rossi","yuli@gmail.com",passwordEncoder.encode("yuli123"));
			Student student3 = new Student("Juako","Rossi","juako@gmail.com", passwordEncoder.encode("juako123"));
			Student student4 = new Student("Juan","Lopez","juan@gmail.com", passwordEncoder.encode("juan23"));
			Student student5 = new Student("Julian","Perez","juli@gmail.com", passwordEncoder.encode("juli123"));
			Student student6 = new Student("Agustina","Gonzales","agustina@gmail.com", passwordEncoder.encode("agus123"));
			Student student7 = new Student("Yolanda","Ponce","yolanda@gmail.com", passwordEncoder.encode("yolanda123"));

			studentService.save(student1);
			studentService.save(student2);
			studentService.save(student3);
			studentService.save(student4);
			studentService.save(student5);
			studentService.save(student6);
			studentService.save(student7);

			Course course1 = new Course("Java Course", LocalDate.now(),LocalDate.now().plusMonths(6));
			Course course2 = new Course("Front-End Course", LocalDate.now(),LocalDate.now().plusMonths(6));
			Course course3 = new Course("Big Data Course", LocalDate.now(),LocalDate.now().plusMonths(4));
			Course course4 = new Course("Prompt-Engineering Course", LocalDate.now(),LocalDate.now().plusMonths(3));
			TeacherCourse teacherCourse1 = new TeacherCourse(Period.MORNING);
			TeacherCourse teacherCourse2 = new TeacherCourse(Period.MORNING);
			TeacherCourse teacherCourse3 = new TeacherCourse(Period.AFTERNOON);
			TeacherCourse teacherCourse4 = new TeacherCourse(Period.NIGHT);
			TeacherCourse teacherCourse5 = new TeacherCourse(Period.AFTERNOON);

			courseService.save(course1);
			courseService.save(course2);
			courseService.save(course3);
			courseService.save(course4);
			teacherService.save(teacher1);
			teacherService.save(teacher2);
			teacherService.save(teacher3);


			teacher1.asignCourse(teacherCourse1);
			course1.addTeacher(teacherCourse1);

			teacher2.asignCourse(teacherCourse2);
			course2.addTeacher(teacherCourse2);

			teacher1.asignCourse(teacherCourse3);
			course3.addTeacher(teacherCourse3);
			teacher2.asignCourse(teacherCourse5);
			course3.addTeacher(teacherCourse5);

			teacher1.asignCourse(teacherCourse4);
			course4.addTeacher(teacherCourse4);

			teacherCourseService.save(teacherCourse1);
			teacherCourseService.save(teacherCourse2);
			teacherCourseService.save(teacherCourse3);
			teacherCourseService.save(teacherCourse4);
			teacherCourseService.save(teacherCourse5);
			courseService.save(course1);
			courseService.save(course2);
			courseService.save(course3);
			courseService.save(course4);
			teacherService.save(teacher1);
			teacherService.save(teacher2);


			StudentTeacherCourse studentTeacherCourse1 = new StudentTeacherCourse(LocalDateTime.now());
			StudentTeacherCourse studentTeacherCourse2 = new StudentTeacherCourse(LocalDateTime.now().plusHours(1));
			StudentTeacherCourse studentTeacherCourse3 = new StudentTeacherCourse(LocalDateTime.now().plusHours(2));
			StudentTeacherCourse studentTeacherCourse4 = new StudentTeacherCourse(LocalDateTime.now().plusHours(3));
			StudentTeacherCourse studentTeacherCourse5 = new StudentTeacherCourse(LocalDateTime.now().plusHours(4));
			StudentTeacherCourse studentTeacherCourse6 = new StudentTeacherCourse(LocalDateTime.now().plusHours(5));
			StudentTeacherCourse studentTeacherCourse7 = new StudentTeacherCourse(LocalDateTime.now().plusHours(6));
			StudentTeacherCourse studentTeacherCourse8 = new StudentTeacherCourse(LocalDateTime.now().plusHours(7));


			studentTeacherCourseService.save(studentTeacherCourse1);
			studentTeacherCourseService.save(studentTeacherCourse2);
			studentTeacherCourseService.save(studentTeacherCourse3);
			studentTeacherCourseService.save(studentTeacherCourse4);
			studentTeacherCourseService.save(studentTeacherCourse5);
			studentTeacherCourseService.save(studentTeacherCourse6);
			studentTeacherCourseService.save(studentTeacherCourse7);
			studentTeacherCourseService.save(studentTeacherCourse8);

			teacherCourse1.addStudent(studentTeacherCourse1);
			student1.addCourse(studentTeacherCourse1);
			student1.setHasAnyCourse(true);

			teacherCourse2.addStudent(studentTeacherCourse2);
			student2.addCourse(studentTeacherCourse2);
			student2.setHasAnyCourse(true);


			teacherCourse3.addStudent(studentTeacherCourse3);
			student2.addCourse(studentTeacherCourse3);

			teacherCourse4.addStudent(studentTeacherCourse4);
			student3.addCourse(studentTeacherCourse4);
			student3.setHasAnyCourse(true);


			teacherCourse4.addStudent(studentTeacherCourse5);
			student4.addCourse(studentTeacherCourse5);
			student4.setHasAnyCourse(true);


			teacherCourse2.addStudent(studentTeacherCourse6);
			student5.addCourse(studentTeacherCourse6);
			student5.setHasAnyCourse(true);


			teacherCourse3.addStudent(studentTeacherCourse7);
			student6.addCourse(studentTeacherCourse7);
			student6.setHasAnyCourse(true);


			teacherCourse1.addStudent(studentTeacherCourse8);
			student7.addCourse(studentTeacherCourse8);
			student7.setHasAnyCourse(true);


			studentTeacherCourseService.save(studentTeacherCourse1);
			studentTeacherCourseService.save(studentTeacherCourse2);
			studentTeacherCourseService.save(studentTeacherCourse3);
			studentTeacherCourseService.save(studentTeacherCourse4);
			studentTeacherCourseService.save(studentTeacherCourse5);
			studentTeacherCourseService.save(studentTeacherCourse6);
			studentTeacherCourseService.save(studentTeacherCourse7);
			studentTeacherCourseService.save(studentTeacherCourse8);

			teacherCourseService.save(teacherCourse1);
			teacherCourseService.save(teacherCourse2);
			teacherCourseService.save(teacherCourse3);
			teacherCourseService.save(teacherCourse4);

			studentService.save(student1);
			studentService.save(student2);
			studentService.save(student3);
			studentService.save(student4);
			studentService.save(student5);
			studentService.save(student6);
			studentService.save(student7);


			Admin admin = new Admin("Admin","Admin","admin1@fifthimpact.com", passwordEncoder.encode("12345"));
			Admin admin2 = new Admin("Admin2","Admin2","admin2@gmail.com", passwordEncoder.encode("12345"));
			adminService.save(admin);
			adminService.save(admin2);


		});

		}

}
