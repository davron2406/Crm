package com.example.crm.payload;

import lombok.Data;

@Data
public class RegisterDto {

    private String fullName;

    private String email;
    private String password;
}
