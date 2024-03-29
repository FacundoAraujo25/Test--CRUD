package com.araujofacundo.testtecquinto.Controllers;

import com.araujofacundo.testtecquinto.Models.subclass.Admin;
import com.araujofacundo.testtecquinto.Services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping("/admins")
    public List<Admin> getAllAdmins(){
        return adminService.findAllAdmins();
    }

    @GetMapping("/admins/{id}")
    public Admin getAdmin (@PathVariable Long id){
        return adminService.findById(id);
    }

    @PostMapping("/admins")
    public ResponseEntity<Object> createAdmin(@RequestParam String firstName,@RequestParam String lastName,
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

        if(!email.endsWith("@fifthimpact.com")){
            return new ResponseEntity<>("This is not an email to be an admin", HttpStatus.FORBIDDEN);
        }

        if(adminService.findByEmail(email) != null){
            return new ResponseEntity<>("There is an admin using this email", HttpStatus.FORBIDDEN);
        }

        if(password.isBlank()){
            return new ResponseEntity<>("Missing password", HttpStatus.FORBIDDEN);
        }

        Admin admin = new Admin(firstName, lastName, email, password);
        adminService.save(admin);

        return new ResponseEntity<>(HttpStatus.CREATED);

    }

    @DeleteMapping("/admins/{id}")
    public ResponseEntity<Object> deleteAdmin (@PathVariable Long id){
        Admin admin = adminService.findById(id);

        if(admin == null){
            return new ResponseEntity<>("Admin not found", HttpStatus.FORBIDDEN);
        }

        admin.setActiveAdmin(false);
        adminService.save(admin);

        return new ResponseEntity<>("Admin succesfully unregistered",HttpStatus.ACCEPTED);

    }

}
