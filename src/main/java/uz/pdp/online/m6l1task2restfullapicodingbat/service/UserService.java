package uz.pdp.online.m6l1task2restfullapicodingbat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import uz.pdp.online.m6l1task2restfullapicodingbat.entity.User;
import uz.pdp.online.m6l1task2restfullapicodingbat.payload.Result;
import uz.pdp.online.m6l1task2restfullapicodingbat.repository.UserRepository;

import java.util.Optional;

@Service
@Component
public class UserService {
    @Autowired
    UserRepository userRepository;

    public Result addUser(User user){
        boolean existsByEmail = userRepository.existsByEmail(user.getEmail());
        if (existsByEmail)
            return new Result("This user already exists",false);
        userRepository.save(user);
        return new Result("User saved",true);

    }

    public Page<User> getUserPage(Integer page){

        Pageable pageable= PageRequest.of(page,3);
        Page<User> userPage = userRepository.findAll(pageable);
        return userPage;
    }


    public User getOneUser(Integer id){

        Optional<User> optionalUser = userRepository.findById(id);
        return optionalUser.orElse(null);
    }

    public Result editUser(Integer id,User user){
        Optional<User> optionalUser = userRepository.findById(id);
        if (!optionalUser.isPresent())
            return new Result("User not found by id",false);

        boolean andIdNot = userRepository.existsByEmailAndIdNot(user.getEmail(), id);
        if (andIdNot)
            return new Result("This email already exists",false);

        User editUser = optionalUser.get();
        editUser.setEmail(user.getEmail());
        editUser.setPassword(user.getPassword());
        userRepository.save(editUser);
        return new Result("User edited",true);

    }

    public Result deleteUser(Integer id){

        try {
            userRepository.deleteById(id);
            return new Result("user deleted",true);
        }catch (Exception e ){
            return new Result("error delete",false);

        }

    }



}

