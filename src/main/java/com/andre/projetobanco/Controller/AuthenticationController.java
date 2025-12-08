package com.andre.projetobanco.Controller;

import com.andre.projetobanco.DTO.AccountLoginDTO;
import com.andre.projetobanco.DTO.LoginResponseDTO;
import com.andre.projetobanco.DTO.UserLoginDTO;
import com.andre.projetobanco.Domain.*;
import com.andre.projetobanco.Infra.Security.TokenService;
import com.andre.projetobanco.Services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AccountService accountService;

    @PostMapping("/auth/account/login")
    public ResponseEntity<LoginResponseDTO> loginAccount(@RequestBody @Validated AccountLoginDTO data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.getAccountNumber(), data.getPassword());
        var auth = authenticationManager.authenticate(usernamePassword);

        Account account = accountService.findByAccountNumber(data.getAccountNumber());

        if (account instanceof CurrentAccount) {
            var token = tokenService.generateAccountToken(((CurrentAccount) auth.getPrincipal()));
            return ResponseEntity.ok(new LoginResponseDTO(token));
        } else if (account instanceof SavingsAccount) {
            var token = tokenService.generateAccountToken(((SavingsAccount) auth.getPrincipal()));
            return ResponseEntity.ok(new LoginResponseDTO(token));
        } else if (account instanceof SalaryAccount) {
            var token = tokenService.generateAccountToken(((SalaryAccount) auth.getPrincipal()));
            return ResponseEntity.ok(new LoginResponseDTO(token));
        }
        
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/auth/employee/login")
    public ResponseEntity<LoginResponseDTO> loginUser(@RequestBody @Validated UserLoginDTO data) {
        return ResponseEntity.ok().build();

        /*
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.getCpf(), data.getPassword());
        var auth = authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateUserToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
        */
    }
}
