package com.araujofacundo.testtecquinto.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository{

    User findByEmailAuth(String email);
    com.araujofacundo.testtecquinto.Models.supclass.User findByEmail(String email) throws Exception;

}
