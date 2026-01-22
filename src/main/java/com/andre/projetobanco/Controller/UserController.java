package com.andre.projetobanco.Controller;

import com.andre.projetobanco.DTO.Users.UserCreationDTO;
import com.andre.projetobanco.Domain.User;
import com.andre.projetobanco.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<User> findByCpf(@PathVariable String cpf) {
        return ResponseEntity.ok().body(userService.findByCpf(cpf));
    }

    @PostMapping
    public ResponseEntity<User> save(@RequestBody UserCreationDTO user) {
        User newUser = userService.createUser(user);
        return ResponseEntity.ok().body(newUser);
    }
}
