package com.araujofacundo.testtecquinto.Services.Implement;

import com.araujofacundo.testtecquinto.Models.subclass.Admin;
import com.araujofacundo.testtecquinto.Repositories.AdminRepository;
import com.araujofacundo.testtecquinto.Services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImplement implements AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Override
    public void save(Admin admin) {
        adminRepository.save(admin);
    }

    @Override
    public List<Admin> findAllAdmins() {
        return adminRepository.findAll();
    }

    @Override
    public Admin findById(Long id) {
        return adminRepository.findById(id).orElse(null);
    }

    @Override
    public Admin findByEmail(String email) {
        return adminRepository.findByEmail(email);
    }
}
