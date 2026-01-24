package com.andre.projetobanco.Controller;

import com.andre.projetobanco.DTO.Account.AccountCreationDTO;
import com.andre.projetobanco.DTO.Account.AccountResponseDTO;
import com.andre.projetobanco.Domain.Account;
import com.andre.projetobanco.Domain.User;
import com.andre.projetobanco.Services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping
    public ResponseEntity<Account> save(@RequestBody AccountCreationDTO account) {
        Account newAccount = accountService.createAccount(account);
        return ResponseEntity.ok().body(newAccount);
    }

    @GetMapping("/{accountNumber}")
    public ResponseEntity<AccountResponseDTO> getByAccountNumber(@PathVariable String accountNumber, @AuthenticationPrincipal User loggedUser) {
        Account account = accountService.findByAccountNumberIfAllowed(accountNumber, loggedUser);
        return ResponseEntity.ok(new AccountResponseDTO(account));
    }
}
