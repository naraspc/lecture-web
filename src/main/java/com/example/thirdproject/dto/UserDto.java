package com.example.thirdproject.dto;

import com.example.thirdproject.constant.Department;
import com.example.thirdproject.constant.Role;
import lombok.Getter;

@Getter
public class UserDto {

    public static class userRegisterDto {
        private String email;
        private String password;
        private Enum<Department> department;
        private Enum<Role> role;
    }

    public static class userLoginDto {
        private String email;
        private String password;
    }

}