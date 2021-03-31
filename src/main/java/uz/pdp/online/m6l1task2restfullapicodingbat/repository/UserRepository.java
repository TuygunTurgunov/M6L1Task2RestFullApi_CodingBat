package uz.pdp.online.m6l1task2restfullapicodingbat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.online.m6l1task2restfullapicodingbat.entity.User;

import javax.validation.constraints.NotNull;

public interface UserRepository extends JpaRepository<User,Integer> {

    boolean existsByEmail(String email);
    boolean existsByEmailAndIdNot(@NotNull(message = "email not be empty") String email, Integer id);


}
