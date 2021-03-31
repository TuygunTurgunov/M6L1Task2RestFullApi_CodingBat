package uz.pdp.online.m6l1task2restfullapicodingbat.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @Column(nullable = false)
    private String name;

    private String description;

    @ManyToOne
    private Language language;

//    @NotNull(message = "list lang not be empty")
//    @ManyToMany
//    private List<Language> language;

}
