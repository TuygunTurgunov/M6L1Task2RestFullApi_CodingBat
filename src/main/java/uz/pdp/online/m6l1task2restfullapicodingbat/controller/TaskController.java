package uz.pdp.online.m6l1task2restfullapicodingbat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import uz.pdp.online.m6l1task2restfullapicodingbat.entity.Task;
import uz.pdp.online.m6l1task2restfullapicodingbat.payload.Result;
import uz.pdp.online.m6l1task2restfullapicodingbat.payload.TaskDto;
import uz.pdp.online.m6l1task2restfullapicodingbat.repository.CategoryRepository;
import uz.pdp.online.m6l1task2restfullapicodingbat.repository.LanguageRepository;
import uz.pdp.online.m6l1task2restfullapicodingbat.service.TaskService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/task")
public class TaskController {
    @Autowired
    TaskService taskService;

    @PostMapping
    public ResponseEntity<?> addTask(@Valid @RequestBody TaskDto taskDto){

        Result result = taskService.addTask(taskDto);
        return ResponseEntity.status(result.getSuccess()?201:409).body(result);
    }
    @GetMapping
    public ResponseEntity<?> getTaskPage(@RequestParam Integer page){
        Page<Task> taskPage = taskService.getTaskPage(page);
        return ResponseEntity.ok(taskPage);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?>getOneTask(@PathVariable Integer id){

        Task oneTask = taskService.getOneTask(id);
        return ResponseEntity.status(oneTask!=null?202:404).body(oneTask);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?>editTask(@PathVariable Integer id,@Valid @RequestBody TaskDto taskDto){

        Result result = taskService.editTask(id, taskDto);
        return  ResponseEntity.status(result.getSuccess()?202:409).body(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?>deleteTask(@PathVariable Integer id){
        Result result = taskService.deleteTask(id);
        return ResponseEntity.status(result.getSuccess()?202:409).body(result);
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
