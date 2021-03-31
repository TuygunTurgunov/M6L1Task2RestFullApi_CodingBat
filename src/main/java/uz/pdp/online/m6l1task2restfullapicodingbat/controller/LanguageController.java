package uz.pdp.online.m6l1task2restfullapicodingbat.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import uz.pdp.online.m6l1task2restfullapicodingbat.entity.Language;
import uz.pdp.online.m6l1task2restfullapicodingbat.payload.Result;
import uz.pdp.online.m6l1task2restfullapicodingbat.service.LanguageService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/api/language")
public class LanguageController {

    @Autowired
    LanguageService languageService;

    @PostMapping
    public ResponseEntity<?>addLan(@Valid @RequestBody Language language){
        Result result = languageService.addLanguage(language);
        return ResponseEntity.status(result.getSuccess()?201:409).body(result);
    }
    @GetMapping
    public ResponseEntity<?>getLanPage(@RequestParam Integer page){
        Page<Language> languagePage = languageService.getLanguagePage(page);
        return ResponseEntity.ok(languagePage);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?>getOneLan(@PathVariable Integer id){
        Language oneLanguage = languageService.getOneLanguage(id);
        return ResponseEntity.status(oneLanguage!=null?202:404).body(oneLanguage);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?>editLan(@PathVariable Integer id,@Valid @RequestBody Language language ){
        Result result = languageService.editLanguage(id, language);
        return ResponseEntity.status(result.getSuccess()?202:409).body(result);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?>deleteLan(@PathVariable Integer id){

        Result result = languageService.deleteLanguage(id);
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
