package uz.pdp.online.m6l1task2restfullapicodingbat.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String text;

    private String showSolution;

    private String showHint;

    @Column(nullable = false)
    private String methodName;

    @Column(nullable = false)
    private Boolean hasStar;

    @ManyToOne
    private Category category;

    @ManyToOne
    private Language language;

}