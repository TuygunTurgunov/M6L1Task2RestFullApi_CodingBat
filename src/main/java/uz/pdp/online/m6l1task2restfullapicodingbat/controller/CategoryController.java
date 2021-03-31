package uz.pdp.online.m6l1task2restfullapicodingbat.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import uz.pdp.online.m6l1task2restfullapicodingbat.entity.Category;
import uz.pdp.online.m6l1task2restfullapicodingbat.payload.CategoryDto;
import uz.pdp.online.m6l1task2restfullapicodingbat.payload.Result;
import uz.pdp.online.m6l1task2restfullapicodingbat.service.CategoryService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @PostMapping
    public ResponseEntity<?>addCat(@Valid @RequestBody CategoryDto categoryDto){
        Result result = categoryService.addCat(categoryDto);
        return ResponseEntity.status(result.getSuccess()?201:409).body(result);
    }


    @GetMapping
    public ResponseEntity<?>getCategoryPage(@RequestParam Integer page){
        Page<Category> categoryPage = categoryService.getCategoryPage(page);
        return ResponseEntity.ok(categoryPage);
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getOneCategory(@PathVariable Integer id){
        Category oneCategory = categoryService.getOneCategory(id);
        return ResponseEntity.status(oneCategory!=null?202:404).body(oneCategory);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editCategory(@PathVariable Integer id,@Valid @RequestBody CategoryDto categoryDto){
        Result result = categoryService.editCategory(id, categoryDto);
        return ResponseEntity.status(result.getSuccess()?202:409).body(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCate(@PathVariable Integer id){
        Result result = categoryService.deleteCate(id);
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
