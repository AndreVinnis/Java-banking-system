package com.andre.projetobanco.Controller;

import com.andre.projetobanco.DTO.AccountCreationDTO;
import com.andre.projetobanco.Domain.Account;
import com.andre.projetobanco.Services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;


    @GetMapping("/accountNumber/{accountNumber}")
    public ResponseEntity<Account> findByAccountNumber(@PathVariable String accountNumber) {
        return ResponseEntity.ok().body(accountService.findByAccountNumber(accountNumber));
    }

    @PostMapping
    public ResponseEntity<Account> save(@RequestBody AccountCreationDTO accountCreationDTO) {
        Account newAccount = accountService.createAccount(accountCreationDTO);
        return ResponseEntity.ok().body(newAccount);
    }

}
