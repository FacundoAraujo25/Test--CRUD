package com.araujofacundo.testtecquinto.Models.supclass;

import com.araujofacundo.testtecquinto.Models.Role;
import lombok.*;

import javax.persistence.*;

@MappedSuperclass
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class User {

    @NonNull
    private String firstName, lastName, email, password;

    @NonNull
    private Role userRole;

    public String getFullName(){
        return this.getFirstName() + " " + this.getLastName();
    }
}
