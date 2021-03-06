package uz.pdp.online.m6l1task2restfullapicodingbat.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private  String text;

    @ManyToOne
    private  Task task;

    @ManyToOne
    private User user;

    @Column(nullable = false)
    private Boolean isCorrect;


}