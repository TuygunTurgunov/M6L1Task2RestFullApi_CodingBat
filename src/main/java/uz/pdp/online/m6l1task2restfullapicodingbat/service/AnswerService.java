package uz.pdp.online.m6l1task2restfullapicodingbat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.pdp.online.m6l1task2restfullapicodingbat.entity.Answer;
import uz.pdp.online.m6l1task2restfullapicodingbat.entity.Task;
import uz.pdp.online.m6l1task2restfullapicodingbat.entity.User;
import uz.pdp.online.m6l1task2restfullapicodingbat.payload.AnswerDto;
import uz.pdp.online.m6l1task2restfullapicodingbat.payload.Result;
import uz.pdp.online.m6l1task2restfullapicodingbat.repository.AnswerRepository;
import uz.pdp.online.m6l1task2restfullapicodingbat.repository.TaskRepository;
import uz.pdp.online.m6l1task2restfullapicodingbat.repository.UserRepository;

import java.util.Optional;

@Service
public class AnswerService {
    @Autowired
    AnswerRepository answerRepository;

    @Autowired
    TaskRepository taskRepository;
    @Autowired
    UserRepository userRepository;

    public Result addAnswer(AnswerDto answerDto){
        Optional<User> optionalUser = userRepository.findById(answerDto.getUserId());
        if (!optionalUser.isPresent())
            return new Result("User not found by id",false);

        Optional<Task> optionalTask = taskRepository.findById(answerDto.getTaskId());
        if (!optionalTask.isPresent())
            return new Result("TAsk is not found by id",false);

        boolean exists = answerRepository.existsByTaskAndUser(optionalTask.get(), optionalUser.get());
        if (exists)
            return new Result("answer alread exists",false);

        Answer answer=new Answer();
        answer.setTask(optionalTask.get());
        answer.setIsCorrect(answerDto.getIsCorrect());
        answer.setText(answerDto.getText());
        answer.setUser(optionalUser.get());
        answerRepository.save(answer);
        return new Result("Answer saved",true);
    }


    public Page<Answer>getAnswerPage(Integer page){
        Pageable pageable= PageRequest.of(page,3);
        return answerRepository.findAll(pageable);

    }
    public Answer getOneAnswer(Integer id){
        Optional<Answer> answerOptional = answerRepository.findById(id);
        return answerOptional.orElse(null);
    }


    public Result editAnswer(Integer id,AnswerDto answerDto){
        Optional<Answer> optionalAnswer = answerRepository.findById(id);
        if (!optionalAnswer.isPresent())
            return new Result("Answer not found by id",false);

        Optional<User> optionalUser = userRepository.findById(answerDto.getUserId());
        if (!optionalUser.isPresent())
            return new Result("User not found by id",false);

        Optional<Task> optionalTask = taskRepository.findById(answerDto.getTaskId());
        if (!optionalTask.isPresent())
            return new Result("TAsk is not found by id",false);

//        boolean exists = answerRepository.existsByTaskAndUserAndIdNot(optionalTask.get(), optionalUser.get(), id);


        Answer answer = optionalAnswer.get();
        answer.setUser(optionalUser.get());
        answer.setTask(optionalTask.get());
        answer.setIsCorrect(answerDto.getIsCorrect());
        answer.setText(answerDto.getText());
        answerRepository.save(answer);
        return new Result("answer edited",true);


    }

    public Result deleteAnswer(Integer id){

        try {
            answerRepository.deleteById(id);
            return new Result("Answer deleted",true);
        }catch (Exception e){
            return new Result("error",false);
        }



    }









}
