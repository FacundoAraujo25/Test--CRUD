package com.araujofacundo.testtecquinto.configurations;


import com.araujofacundo.testtecquinto.Repositories.AdminRepository;
import com.araujofacundo.testtecquinto.Repositories.Implement.UserRepositoryImplement;
import com.araujofacundo.testtecquinto.Services.StudentService;
import com.araujofacundo.testtecquinto.Services.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class WebAuthentication extends GlobalAuthenticationConfigurerAdapter {

    @Autowired
    UserRepositoryImplement userRepositoryImplement;

    @Override
    public void init(AuthenticationManagerBuilder auth) throws Exception{

        auth.userDetailsService(userEmail -> userRepositoryImplement.findByEmailAuth(userEmail));

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

}
