package com.araujofacundo.testtecquinto.configurations;

import com.araujofacundo.testtecquinto.Models.subclass.Admin;
import com.araujofacundo.testtecquinto.Models.subclass.Student;
import com.araujofacundo.testtecquinto.Models.subclass.Teacher;
import com.araujofacundo.testtecquinto.Repositories.AdminRepository;
import com.araujofacundo.testtecquinto.Services.StudentService;
import com.araujofacundo.testtecquinto.Services.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class WebAuthentication extends GlobalAuthenticationConfigurerAdapter {

    @Autowired
    TeacherService teacherService;

    @Autowired
    StudentService studentService;

    @Autowired
    AdminRepository adminRepository;

    @Override
    public void init(AuthenticationManagerBuilder auth) throws Exception{

        auth.userDetailsService(userEmail ->{
            Student student = studentService.findByEmail(userEmail);
            Teacher teacher = teacherService.findByEmail(userEmail);
            Admin admin = adminRepository.findByEmail(userEmail);

            if (student != null){
                System.out.println("student log in");
                return new User(student.getEmail(), student.getPassword(), AuthorityUtils.createAuthorityList("STUDENT"));
            }
            if (teacher != null && teacher.getEmail().endsWith("@teachersFI.com")){
                System.out.println("teacher log in");
                return new User(teacher.getEmail(), teacher.getPassword(), AuthorityUtils.createAuthorityList("TEACHER"));
            }
            if(admin != null && userEmail.contains("@fifthimpact.com"))
            {
                System.out.println("admin log in");
                return new User(admin.getEmail(), admin.getPassword(), AuthorityUtils.createAuthorityList("ADMIN"));
            }
            throw new UsernameNotFoundException("Usuario no registrado: " + userEmail);
        });

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

}
