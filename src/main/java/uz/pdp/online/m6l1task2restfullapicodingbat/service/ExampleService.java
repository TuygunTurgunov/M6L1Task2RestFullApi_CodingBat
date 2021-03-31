package uz.pdp.online.m6l1task2restfullapicodingbat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.pdp.online.m6l1task2restfullapicodingbat.entity.Example;
import uz.pdp.online.m6l1task2restfullapicodingbat.entity.Task;
import uz.pdp.online.m6l1task2restfullapicodingbat.payload.ExampleDto;
import uz.pdp.online.m6l1task2restfullapicodingbat.payload.Result;
import uz.pdp.online.m6l1task2restfullapicodingbat.repository.ExampleRepository;
import uz.pdp.online.m6l1task2restfullapicodingbat.repository.TaskRepository;

import java.util.Optional;

@Service
public class ExampleService {
    @Autowired
    ExampleRepository exampleRepository;
    @Autowired
    TaskRepository taskRepository;

    public Result addEx(ExampleDto exampleDto){
        Optional<Task> optionalTask = taskRepository.findById(exampleDto.getTaskId());
        if (!optionalTask.isPresent())
            return new Result("Task not found by id",false);

        boolean exists = exampleRepository.existsByTextAndTask(exampleDto.getText(), optionalTask.get());
        if (exists)
            return new Result("Example for this task already exists",false);

        Example example=new Example();
        example.setText(exampleDto.getText());
        example.setTask(optionalTask.get());
        exampleRepository.save(example);
        return new Result("example saved",true);


    }

    public Page<Example> getExPage(Integer page){
        Pageable pageable= PageRequest.of(page,3);
        return exampleRepository.findAll(pageable);
    }
    public Example getOneEx(Integer id){
        Optional<Example> optionalExample = exampleRepository.findById(id);
        return optionalExample.orElse(null);
    }

    public Result editEx(Integer id,ExampleDto exampleDto){
        Optional<Example> optionalExample = exampleRepository.findById(id);
        if (!optionalExample.isPresent())
            return new Result("example not find by id",false);

        Optional<Task> optionalTask = taskRepository.findById(exampleDto.getTaskId());
        if (!optionalTask.isPresent())
            return new Result("Task not found",false);

        boolean exists = exampleRepository.existsByTextAndTaskAndIdNot(exampleDto.getText(), optionalTask.get(),id);
        if (exists)
            return new Result("already exists",false);


        Example example = optionalExample.get();
        example.setTask(optionalTask.get());
        example.setText(exampleDto.getText());
        exampleRepository.save(example);
        return new Result("example edited",true);
    }

    public Result deleteEx(Integer id){
        try {
            exampleRepository.deleteById(id);
            return new Result("deleted",true);
        }catch (Exception e){
        return new Result("Error",false);
        }

    }



}
