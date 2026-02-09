package com.andre.projetobanco.Services;

import com.andre.projetobanco.DTO.AccountCreationDTO;
import com.andre.projetobanco.Domain.*;
import com.andre.projetobanco.Repository.AccountRepository;
import jakarta.transaction.Transactional;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class AccountService {
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;
    private final String DEFAULT_AGENCY = "0001";

    @Autowired
    public AccountService(AccountRepository accountRepository, PasswordEncoder passwordEncoder, UserService userService) {
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
    }

    public Account findById(Long id) {
        Optional<Account> account = accountRepository.findById(id);
        return account.orElseThrow(() -> new ObjectNotFoundException(account, "Account not found"));
    }

    public Account findByAccountNumber(String accountNumber) {
        Optional<Account> account = accountRepository.findByAccountNumber(accountNumber);
        return account.orElseThrow(() -> new ObjectNotFoundException(account, "Account not found"));
    }

    @Transactional
    public Account createAccount(AccountCreationDTO account) {
        Account newAccount;

        if(account.getType() == "current"){
            newAccount = new CurrentAccount();
            ((CurrentAccount) newAccount).setOverdrawLimit(BigDecimal.valueOf(500));
        }
        else if(account.getType() == "savings"){
            newAccount = new SavingsAccount();
        }
        else{
            newAccount = new SalaryAccount();
        }

        newAccount.setUser((userService.findByCpf(account.getOwnerCpf())));
        newAccount.setAgency(DEFAULT_AGENCY);
        newAccount.setTransactionPinHash(passwordEncoder.encode(account.getPassword()));

        accountRepository.save(newAccount);

        newAccount.setAccountNumber(generateAccountNumber(newAccount.getId()));

        return accountRepository.save(newAccount);
    }

    private String generateAccountNumber(Long accountId) {
        String baseNumber = String.format("%06d", accountId);

        int checkDigit = (int) (accountId % 10);

        return baseNumber + "-" + checkDigit;
    }
}
