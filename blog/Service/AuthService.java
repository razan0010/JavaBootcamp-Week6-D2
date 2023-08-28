package com.example.blog.Service;

import com.example.blog.Model.User;
import com.example.blog.Repository.AuthRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthRepository authRepository;

    public List<User> getAllUsers(){
        return authRepository.findAll();
    }

    public void register(@Valid User user){

        String hash = new BCryptPasswordEncoder().encode(user.getPassword());

        user.setPassword(hash);
        user.setRole("USER");

        authRepository.save(user);
    }

}