package com.andre.projetobanco.Controller;

import com.andre.projetobanco.DTO.Account.AccountCreationDTO;
import com.andre.projetobanco.Domain.Account;
import com.andre.projetobanco.Services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping
    public ResponseEntity<Account> save(@RequestBody AccountCreationDTO account) {
        System.out.println("DEBUG: Cheguei no Controller!");
        Account newAccount = accountService.createAccount(account);
        return ResponseEntity.ok().body(newAccount);
    }
}
