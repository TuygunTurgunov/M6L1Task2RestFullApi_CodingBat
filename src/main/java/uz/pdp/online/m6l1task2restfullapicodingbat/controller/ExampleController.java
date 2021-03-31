package uz.pdp.online.m6l1task2restfullapicodingbat.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import uz.pdp.online.m6l1task2restfullapicodingbat.entity.Example;
import uz.pdp.online.m6l1task2restfullapicodingbat.payload.ExampleDto;
import uz.pdp.online.m6l1task2restfullapicodingbat.payload.Result;
import uz.pdp.online.m6l1task2restfullapicodingbat.service.ExampleService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/example")
public class ExampleController {
    @Autowired
    ExampleService exampleService;


    @PostMapping
    public ResponseEntity<?>addEx(@Valid @RequestBody ExampleDto exampleDto){
        Result result = exampleService.addEx(exampleDto);
        return ResponseEntity.status(result.getSuccess()?201:409).body(result);
    }


    @GetMapping
    public ResponseEntity<?> getExPage(@RequestParam Integer page){
        Page<Example> examplePage = exampleService.getExPage(page);
        return ResponseEntity.ok(examplePage);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getOneEx(@PathVariable Integer id){
        Example oneEx = exampleService.getOneEx(id);

        return ResponseEntity.status(oneEx!=null?202:404).body(oneEx);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editEx(@PathVariable Integer id,@Valid @RequestBody ExampleDto exampleDto){

        Result result = exampleService.editEx(id, exampleDto);
        return ResponseEntity.status(result.getSuccess()?202:409).body(result);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEx(@PathVariable Integer id){

        Result result = exampleService.deleteEx(id);
        return  ResponseEntity.status(result.getSuccess()?202:409).body(result);

    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

}