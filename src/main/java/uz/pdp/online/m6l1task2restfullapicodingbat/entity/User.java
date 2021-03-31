package uz.pdp.online.m6l1task2restfullapicodingbat.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull(message = "email not be empty")
    @Column(nullable = false,unique = true)
    private String email;
    @NotNull(message = "password not be empty")
    private String password;

}