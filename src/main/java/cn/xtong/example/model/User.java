package cn.xtong.example.model;

import lombok.Data;

@Data
public class User {
    private Long id;
    private String username;
    private String password;
    private String sex;
    private String email;
}
