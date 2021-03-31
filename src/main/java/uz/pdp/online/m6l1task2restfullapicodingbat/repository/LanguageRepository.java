package uz.pdp.online.m6l1task2restfullapicodingbat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.online.m6l1task2restfullapicodingbat.entity.Language;

public interface LanguageRepository extends JpaRepository<Language,Integer> {

    boolean existsByName(String name);
    boolean existsByNameAndIdNot(String name, Integer id);



}
