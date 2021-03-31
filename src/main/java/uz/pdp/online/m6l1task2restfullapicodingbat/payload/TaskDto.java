package uz.pdp.online.m6l1task2restfullapicodingbat.payload;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class TaskDto {
    @NotNull(message = "name key is not null.")
    private String name;
    @NotNull(message = "key is not null.")
    private String text;
    private String showSolution;
    private String showHint;
    @NotNull(message = "key is not null.")
    private String methodName;
    @NotNull(message = "key is not null.")
    private Boolean hasStar;
    @NotNull(message = " key is not null.")
    private Integer categoryId;
    @NotNull(message = "key is not null.")
    private Integer languageId;
}
