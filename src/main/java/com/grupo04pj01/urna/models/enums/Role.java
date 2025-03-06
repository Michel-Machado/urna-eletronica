package com.grupo04pj01.urna.models.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;



@AllArgsConstructor
@Getter
@NoArgsConstructor
public enum Role {

    ADMIN("admin"),
    MESARIO("mesario"),
    ELEITOR("eleitor");


    private String role;

}
