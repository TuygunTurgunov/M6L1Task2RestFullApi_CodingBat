package uz.pdp.online.m6l1task2restfullapicodingbat.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import uz.pdp.online.m6l1task2restfullapicodingbat.entity.User;
import uz.pdp.online.m6l1task2restfullapicodingbat.payload.Result;
import uz.pdp.online.m6l1task2restfullapicodingbat.payload.Result;
import uz.pdp.online.m6l1task2restfullapicodingbat.service.UserService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping
    public ResponseEntity<Result> addUser(@Valid @RequestBody User user){
        Result result = userService.addUser(user);
        return ResponseEntity.status(result.getSuccess()?201:409).body(result);
    }

    @GetMapping
    public ResponseEntity<?> getUserPage(@RequestParam Integer page){

        Page<User> userPage = userService.getUserPage(page);

        return ResponseEntity.ok(userPage);
    }

    @GetMapping("/{id}")
    public  ResponseEntity<?> getOneUser(@PathVariable Integer id){

        User oneUser = userService.getOneUser(id);
        return ResponseEntity.status(oneUser!=null?200:404).body(oneUser);
    }

    @PutMapping("/{id}")
    public  ResponseEntity<?> editUser(@PathVariable Integer id,@Valid @RequestBody User user){
        Result result = userService.editUser(id, user);
        return ResponseEntity.status(result.getSuccess()?202:409).body(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Integer id){
        Result result = userService.deleteUser(id);
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