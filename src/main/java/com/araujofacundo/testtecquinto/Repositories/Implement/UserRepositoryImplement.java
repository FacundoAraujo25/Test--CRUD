package com.araujofacundo.testtecquinto.Repositories.Implement;

import com.araujofacundo.testtecquinto.Models.subclass.Admin;
import com.araujofacundo.testtecquinto.Models.subclass.Student;
import com.araujofacundo.testtecquinto.Models.subclass.Teacher;
import com.araujofacundo.testtecquinto.Repositories.AdminRepository;
import com.araujofacundo.testtecquinto.Repositories.UserRepository;
import com.araujofacundo.testtecquinto.Services.StudentService;
import com.araujofacundo.testtecquinto.Services.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserRepositoryImplement implements UserRepository {

    @Autowired
    TeacherService teacherService;

    @Autowired
    StudentService studentService;

    @Autowired
    AdminRepository adminRepository;


    @Override
    public User findByEmailAuth(String email) {

        Student student = studentService.findByEmail(email);
        Teacher teacher = teacherService.findByEmail(email);
        Admin admin = adminRepository.findByEmail(email);


        if (student != null){
            System.out.println("student log in");
            return new User(student.getEmail(), student.getPassword(), AuthorityUtils.createAuthorityList(student.getUserRole().toString()));
        }

        if (teacher != null){
            System.out.println("teacher log in");
            return new User(teacher.getEmail(), teacher.getPassword(), AuthorityUtils.createAuthorityList(teacher.getUserRole().toString()));
        }
        if(admin != null) {
            System.out.println("admin log in");
            return new User(admin.getEmail(), admin.getPassword(), AuthorityUtils.createAuthorityList(admin.getUserRole().toString()));
        }

        throw new UsernameNotFoundException("Usuario no registrado: " + email);
    }

    @Override
    public com.araujofacundo.testtecquinto.Models.supclass.User findByEmail(String email) throws Exception {

        Student student = studentService.findByEmail(email);
        Teacher teacher = teacherService.findByEmail(email);
        Admin admin = adminRepository.findByEmail(email);

        if(student != null){
           return new com.araujofacundo.testtecquinto.Models.supclass.User(student.getFirstName(),student.getLastName(),student.getEmail(),student.getPassword(),student.getUserRole());
        }

        if(teacher != null){
            return new com.araujofacundo.testtecquinto.Models.supclass.User(teacher.getFirstName(),teacher.getLastName(),teacher.getEmail(),teacher.getPassword(),teacher.getUserRole());
        }

        if(admin != null){
            return new com.araujofacundo.testtecquinto.Models.supclass.User(admin.getFirstName(),admin.getLastName(),admin.getEmail(),admin.getPassword(),admin.getUserRole());
        }

        throw new Exception("User does not exist");

    }
}
