package uz.pdp.online.m6l1task2restfullapicodingbat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.online.m6l1task2restfullapicodingbat.entity.Example;
import uz.pdp.online.m6l1task2restfullapicodingbat.entity.Task;

public interface ExampleRepository extends JpaRepository<Example,Integer> {

    boolean existsByTextAndTask(String text, Task task);
    boolean existsByTextAndTaskAndIdNot(String text, Task task, Integer id);

}
