package com.PJ.service;

import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import com.PJ.repository.UserRepository;
import com.PJ.model.User;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder; // ไม่ทำให้เกิดวงจร

    public UserService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    public User registerUser(User u) {
        u.setPassword(passwordEncoder.encode(u.getPassword()));
        return userRepo.save(u);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username)
                            .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return new org.springframework.security.core.userdetails.User(
            user.getUsername(),
            user.getPassword(),
            List.of(new SimpleGrantedAuthority("ROLE_USER"))
        );
    }
}