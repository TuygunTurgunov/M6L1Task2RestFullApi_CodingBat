package uz.pdp.online.m6l1task2restfullapicodingbat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.pdp.online.m6l1task2restfullapicodingbat.entity.Category;
import uz.pdp.online.m6l1task2restfullapicodingbat.entity.Language;
import uz.pdp.online.m6l1task2restfullapicodingbat.entity.Task;
import uz.pdp.online.m6l1task2restfullapicodingbat.payload.Result;
import uz.pdp.online.m6l1task2restfullapicodingbat.payload.TaskDto;
import uz.pdp.online.m6l1task2restfullapicodingbat.repository.CategoryRepository;
import uz.pdp.online.m6l1task2restfullapicodingbat.repository.LanguageRepository;
import uz.pdp.online.m6l1task2restfullapicodingbat.repository.TaskRepository;

import java.util.Optional;

@Service
public class TaskService {
    @Autowired
    TaskRepository taskRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    LanguageRepository languageRepository;

    public Result addTask(TaskDto taskDto){
        Optional<Language> optionalLanguage = languageRepository.findById(taskDto.getLanguageId());
        if (!optionalLanguage.isPresent())
            return new Result("Language not found by id",false);

        Optional<Category> optionalCategory = categoryRepository.findById(taskDto.getCategoryId());
        if (!optionalCategory.isPresent())
            return new Result("Category not found by id",false);

        boolean exists = taskRepository.existsByNameAndCategoryAndLanguage(taskDto.getName(), optionalCategory.get(), optionalLanguage.get());
        if (exists)
            return new Result("task with such kind of name already exists in this category and language",false);

        Task task=new Task();
        task.setLanguage(optionalLanguage.get());
        task.setCategory(optionalCategory.get());
        task.setName(taskDto.getName());
        task.setHasStar(taskDto.getHasStar());
        task.setMethodName(taskDto.getMethodName());
        task.setShowHint(taskDto.getShowHint());
        task.setShowSolution(taskDto.getShowSolution());
        task.setText(taskDto.getText());
        taskRepository.save(task);
        return new Result("Task saved",true);
    }


    public Page<Task>getTaskPage(Integer page){
        Pageable pageable = PageRequest.of(page,5);
        Page<Task> taskPage = taskRepository.findAll(pageable);
        return taskPage;
    }



    public Task getOneTask(Integer id){
        Optional<Task> optionalTask = taskRepository.findById(id);
        return optionalTask.orElse(null);
    }


    public Result editTask(Integer id, TaskDto taskDto){

        Optional<Task> optionalTask = taskRepository.findById(id);
        if (!optionalTask.isPresent())
            return new Result("task not found by id",false);

        Optional<Language> optionalLanguage = languageRepository.findById(taskDto.getLanguageId());
        if (!optionalLanguage.isPresent())
            return new Result("Language not found by id",false);

        Optional<Category> optionalCategory = categoryRepository.findById(taskDto.getCategoryId());
        if (!optionalCategory.isPresent())
            return new Result("Category not found by id",false);

        boolean exists = taskRepository.existsByNameAndCategoryAndLanguageAndIdNot(taskDto.getName(), optionalCategory.get(), optionalLanguage.get(), id);
        if (exists)
            return new Result("task with such kind of name already exists in this category and language",false);

        Task task = optionalTask.get();
        task.setLanguage(optionalLanguage.get());
        task.setCategory(optionalCategory.get());
        task.setName(taskDto.getName());
        task.setHasStar(taskDto.getHasStar());
        task.setMethodName(taskDto.getMethodName());
        task.setShowHint(taskDto.getShowHint());
        task.setShowSolution(taskDto.getShowSolution());
        task.setText(taskDto.getText());
        taskRepository.save(task);
        return new Result("task edited",true);
    }

    public Result deleteTask(Integer id){
        try {
            taskRepository.deleteById(id);
            return new Result("deletes",true);
        }
        catch (Exception e){
            return new Result("error",false);
        }
    }






}
