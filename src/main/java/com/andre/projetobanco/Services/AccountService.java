package com.andre.projetobanco.Services;

import com.andre.projetobanco.DTO.Account.AccountCreationDTO;
import com.andre.projetobanco.Domain.Account;
import com.andre.projetobanco.Domain.CurrentAccount;
import com.andre.projetobanco.Domain.SalaryAccount;
import com.andre.projetobanco.Domain.SavingsAccount;
import com.andre.projetobanco.Repository.AccountRepository;
import jakarta.transaction.Transactional;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class AccountService {

    private final String DEFAULT_AGENCY = "0001";

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Account findById(Long id) {
        Optional<Account> account = accountRepository.findById(id);
        return account.orElseThrow(() -> new ObjectNotFoundException(account, "Account not found"));
    }

    public Account findByAccountNumberIfAllowed(String accountNumber) {
        Optional<Account> account = accountRepository.findByAccountNumber(accountNumber);
        return account.orElseThrow(() -> new ObjectNotFoundException(account, "Account not found"));
    }

    @Transactional
    public Account createAccount(AccountCreationDTO account) {
        Account newAccount;

        switch (account.getAccountType()) {
            case CURRENT -> {
                newAccount = new CurrentAccount();
                ((CurrentAccount) newAccount).setOverdrawLimit(new BigDecimal("500.00"));
                ((CurrentAccount) newAccount).setMaintenanceFee(new BigDecimal("50.00"));
            }
            case SALARY -> {
                newAccount = new SalaryAccount();
            }
            case SAVINGS -> {
                newAccount = new SavingsAccount();
            }
            default ->{throw new IllegalArgumentException("Tipo de conta inválido");}
        }

        newAccount.setUser(userService.findByCpf(account.getOwnerCpf()));
        newAccount.setAgency(DEFAULT_AGENCY);
        newAccount.setTransactionPinHash(passwordEncoder.encode(account.getTransactionPin()));
        newAccount = accountRepository.save(newAccount);
        String formattedNumber = generateAccountNumber(newAccount.getId());
        newAccount.setAccountNumber(formattedNumber);
        return accountRepository.save(newAccount);
    }

    private String generateAccountNumber(Long accountId) {
        String baseNumber = String.format("%06d", accountId);
        int checkDigit = (int) (accountId % 10);
        return baseNumber + "-" + checkDigit;
    }
}
