package com.araujofacundo.testtecquinto.Services;

import com.araujofacundo.testtecquinto.Models.subclass.Admin;

import java.util.List;

public interface AdminService {

    void save(Admin admin);
    List<Admin> findAllAdmins();

    Admin findById(Long id);

    Admin findByEmail(String email);


}
