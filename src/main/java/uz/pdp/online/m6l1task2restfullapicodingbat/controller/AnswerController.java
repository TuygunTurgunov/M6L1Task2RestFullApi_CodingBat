package uz.pdp.online.m6l1task2restfullapicodingbat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import uz.pdp.online.m6l1task2restfullapicodingbat.entity.Answer;
import uz.pdp.online.m6l1task2restfullapicodingbat.payload.AnswerDto;
import uz.pdp.online.m6l1task2restfullapicodingbat.payload.Result;
import uz.pdp.online.m6l1task2restfullapicodingbat.service.AnswerService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/answer")
public class AnswerController {
    @Autowired
    AnswerService answerService;

    @PostMapping
    public ResponseEntity<?>addAnswer(@Valid @RequestBody AnswerDto answerDto){
        Result result = answerService.addAnswer(answerDto);
        return  ResponseEntity.status(result.getSuccess()?201:409).body(result);
    }
    @GetMapping
    public ResponseEntity<?> getAnswerPage(@RequestParam Integer page){

        Page<Answer> answerPage = answerService.getAnswerPage(page);
        return ResponseEntity.ok(answerPage);

    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getOneAnswer(@PathVariable Integer id){

        Answer oneAnswer = answerService.getOneAnswer(id);
        return ResponseEntity.status(oneAnswer!=null?202:404).body(oneAnswer);

    }

    @PutMapping("/{id}")
    public ResponseEntity<?>editAnswer(@PathVariable Integer id,@Valid @RequestBody AnswerDto answerDto){

        Result result = answerService.editAnswer(id, answerDto);
        return  ResponseEntity.status(result.getSuccess()?202:409).body(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?>deleteAnswer(@PathVariable Integer id){
        Result result = answerService.deleteAnswer(id);
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