package com.andre.projetobanco.Config;

import com.andre.projetobanco.Domain.Account;
import com.andre.projetobanco.Domain.Card;
import com.andre.projetobanco.Domain.SavingsAccount;
import com.andre.projetobanco.Domain.User;
import com.andre.projetobanco.Enums.Flag;
import com.andre.projetobanco.Repository.AccountRepository;
import com.andre.projetobanco.Repository.CardRepository;
import com.andre.projetobanco.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.YearMonth;


@Configuration
public class Instantiation implements CommandLineRunner {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private CardRepository cardRepository;

    @Override
    public void run(String... args) throws Exception {

        cardRepository.deleteAllInBatch();
        accountRepository.deleteAllInBatch();
        userRepository.deleteAllInBatch();

        User user = new User(null, "André", "Vinícius", "056060909", "andre@gmail.com", "839189849498", "minhaSenhaForte", LocalDateTime.now());
        Card card = new Card(null, "45127896365214", "789", Flag.MASTERCARD, YearMonth.of(2035, 5), "146382");
        Account account = new SavingsAccount(null, user, "15636", "00752843", BigDecimal.valueOf(4500.00), "senhaDaBoa", card);

        userRepository.save(user);
        cardRepository.save(card);
        accountRepository.save(account);
    }
}
