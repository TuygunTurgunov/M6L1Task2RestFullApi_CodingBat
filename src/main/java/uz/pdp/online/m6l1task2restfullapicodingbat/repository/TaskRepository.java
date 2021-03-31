package uz.pdp.online.m6l1task2restfullapicodingbat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.online.m6l1task2restfullapicodingbat.entity.Category;
import uz.pdp.online.m6l1task2restfullapicodingbat.entity.Language;
import uz.pdp.online.m6l1task2restfullapicodingbat.entity.Task;
import uz.pdp.online.m6l1task2restfullapicodingbat.entity.User;

import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task,Integer> {


    boolean existsByNameAndCategoryAndLanguage(String name, Category category, Language language);

    boolean existsByNameAndCategoryAndLanguageAndIdNot(String name, Category category, Language language, Integer id);



}
