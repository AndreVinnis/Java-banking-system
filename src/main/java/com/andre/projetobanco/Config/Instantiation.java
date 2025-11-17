package com.andre.projetobanco.Config;

import com.andre.projetobanco.Domain.User;
import com.andre.projetobanco.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;


@Configuration
public class Instantiation implements CommandLineRunner {
    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {

        userRepository.deleteAllInBatch();
        User user = new User(null, "André", "Vinícius", "056060909", "andre@gmail.com", "839189849498", "minhaSenhaForte", LocalDateTime.now());
        userRepository.save(user);
    }
}
