package com.andre.projetobanco.Controller;

import com.andre.projetobanco.DTO.LoginResponseDTO;
import com.andre.projetobanco.DTO.Users.UserLoginDTO;
import com.andre.projetobanco.Domain.*;
import com.andre.projetobanco.Infra.Security.TokenService;
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

    @PostMapping("/users/login")
    public ResponseEntity<LoginResponseDTO> loginUser(@RequestBody @Validated UserLoginDTO data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.getCpf(), data.getPassword());
        var auth = authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateUserToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }
}
