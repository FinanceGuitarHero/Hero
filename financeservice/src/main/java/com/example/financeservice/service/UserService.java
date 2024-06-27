package com.example.financeservice.service;

import com.example.financeservice.entity.AppUser;
import com.example.financeservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    UserRepository userRepository;


    public AppUser getUserById(String userIdTg) {
        var user = userRepository.findByTg_id(userIdTg);
        return user.orElse(null);
    }
}
