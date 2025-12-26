package org.company.dummyjson.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.company.dummyjson.models.Genders;

@Data
@AllArgsConstructor
public class LoginResponse {

    private Long id;
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private Genders gender;
    private String image;
    private String token;
}

