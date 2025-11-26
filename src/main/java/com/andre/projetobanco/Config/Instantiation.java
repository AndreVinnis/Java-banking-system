package com.andre.projetobanco.Config;

import com.andre.projetobanco.Domain.Account;
import com.andre.projetobanco.Domain.SavingsAccount;
import com.andre.projetobanco.Domain.User;
import com.andre.projetobanco.Repository.AccountRepository;
import com.andre.projetobanco.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Configuration
public class Instantiation implements CommandLineRunner {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public void run(String... args) throws Exception {

        accountRepository.deleteAllInBatch();
        userRepository.deleteAllInBatch();

        User user = new User(null, "André", "Vinícius", "056060909", "andre@gmail.com", "839189849498", "minhaSenhaForte", LocalDateTime.now());
        Account account = new SavingsAccount(null, user, "15636", "00752843", BigDecimal.valueOf(4500.00), "senhaDaBoa", null);

        userRepository.save(user);
        accountRepository.save(account);
    }
}
