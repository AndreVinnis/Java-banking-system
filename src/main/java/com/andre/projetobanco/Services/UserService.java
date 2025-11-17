package com.andre.projetobanco.Services;

import com.andre.projetobanco.Domain.User;
import com.andre.projetobanco.Repository.UserRepository;
import jakarta.transaction.Transactional;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Transactional
    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User findByCpf(String cpf) {
        Optional<User> user = userRepository.findByCpf(cpf);
        return user.orElseThrow(() -> new ObjectNotFoundException(user, "User not found"));
    }
}
