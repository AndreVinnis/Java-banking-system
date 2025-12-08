package com.andre.projetobanco.Services;

import com.andre.projetobanco.DTO.UserCreationDTO;
import com.andre.projetobanco.Domain.User;
import com.andre.projetobanco.Enums.UserRole;
import com.andre.projetobanco.Repository.UserRepository;
import jakarta.transaction.Transactional;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Transactional
    public User createUser(UserCreationDTO user) {
        User newUser = new User(user);

        String hashPassword = passwordEncoder.encode(user.getPassword());
        newUser.setHashPassword(hashPassword);

        if(newUser.getRole() == null) {
            newUser.setRole(UserRole.CLIENT);
        }

        return userRepository.save(newUser);
    }

    public User findByCpf(String cpf) {
        Optional<User> user = userRepository.findByCpf(cpf);
        return user.orElseThrow(() -> new ObjectNotFoundException(user, "User not found"));
    }
}
