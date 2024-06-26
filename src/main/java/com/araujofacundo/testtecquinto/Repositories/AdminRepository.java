package com.araujofacundo.testtecquinto.Repositories;

import com.araujofacundo.testtecquinto.Models.subclass.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {

    Admin findByEmail (String email);

}
