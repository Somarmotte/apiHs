package com.hsapi.apideck.exception;

import com.hsapi.apideck.model.User;
import com.hsapi.apideck.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(Integer id) {
        super("Could not find user " + id);
    }
}
@Component
class UserCommandLineRunner implements CommandLineRunner {
    @Autowired
    UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception{
        for (User e : this.userRepository.findAll()){
            System.out.println(e.toString());
        }
    }


}
