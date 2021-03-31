package uz.pdp.online.m6l1task2restfullapicodingbat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.online.m6l1task2restfullapicodingbat.entity.Category;
import uz.pdp.online.m6l1task2restfullapicodingbat.entity.Language;

import javax.validation.constraints.NotNull;
import java.util.List;


public interface CategoryRepository extends JpaRepository<Category,Integer> {

//    boolean existsByNameAndLanguage(String name, @NotNull(message = "list lang not be empty") List<Language> language);

    boolean existsByNameAndLanguage(String name, Language language);
    boolean existsByNameAndLanguageAndIdNot(String name, Language language, Integer id);


}
