package uz.pdp.online.m6l1task2restfullapicodingbat.payload;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ExampleDto {
    @NotNull(message = "text not be null")
    private String text;
    @NotNull(message = "task id not be null")
    private Integer taskId;

}
