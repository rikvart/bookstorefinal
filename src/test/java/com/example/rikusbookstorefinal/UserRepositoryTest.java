package com.example.rikusbookstorefinal;


import static org.assertj.core.api.Assertions.assertThat;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.model.User;
import com.example.model.UserRepository;

import org.junit.jupiter.api.Test;



@ExtendWith(SpringExtension.class)
@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository repository;

    @Test
    public void findByUsername() {
        User users = repository.findByUsername("user");
        
        
        assertThat(users.getUsername()).isEqualTo("user");
    }
    
}