package com.andre.projetobanco.Services;

import com.andre.projetobanco.Domain.Account;
import com.andre.projetobanco.Repository.AccountRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;

    public Account findById(Long id) {
        Optional<Account> account = accountRepository.findById(id);
        return account.orElseThrow(() -> new ObjectNotFoundException(account, "Account not found"));
    }

    public Account findByAccountNumber(String accountNumber) {
        Optional<Account> account = accountRepository.findByAccountNumber(accountNumber);
        return account.orElseThrow(() -> new ObjectNotFoundException(account, "Account not found"));
    }
}
