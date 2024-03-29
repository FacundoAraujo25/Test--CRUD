package com.araujofacundo.testtecquinto.Models.supclass;

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

    public String getFullName(){
        return this.getFirstName() + " " + this.getLastName();
    }
}
