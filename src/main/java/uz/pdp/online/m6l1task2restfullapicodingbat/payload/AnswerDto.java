package uz.pdp.online.m6l1task2restfullapicodingbat.payload;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class AnswerDto {
    @NotNull(message = "text not be empty")
    private String text;

    @NotNull(message = "task id not be empty")
    private Integer taskId;

    @NotNull(message = "user id not be empty")
    private Integer userId;

    @NotNull(message = "isCorrect not be empty")
    private Boolean isCorrect;




}
