package uz.pdp.online.m6l1task2restfullapicodingbat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.online.m6l1task2restfullapicodingbat.entity.Answer;
import uz.pdp.online.m6l1task2restfullapicodingbat.entity.Task;
import uz.pdp.online.m6l1task2restfullapicodingbat.entity.User;

public interface AnswerRepository extends JpaRepository<Answer,Integer> {
    boolean existsByTaskAndUser(Task task, User user);
    boolean existsByTaskAndUserAndIdNot(Task task, User user, Integer id);



}
