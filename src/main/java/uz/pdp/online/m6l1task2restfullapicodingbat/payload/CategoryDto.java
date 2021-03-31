package uz.pdp.online.m6l1task2restfullapicodingbat.payload;


import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class CategoryDto {

    @NotNull(message = "name not be empty")
    private String name;
    @NotNull(message = "name not be empty")
    private String description;

    @NotNull(message = "category must be in language so category list  not be empty")
    private Integer languageId;

//    private List<Integer> languageId;

}
