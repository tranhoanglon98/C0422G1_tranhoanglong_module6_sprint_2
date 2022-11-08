package com.example.test;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class service {

    @Autowired
    private TestRepository testRepository;

    public void save(AppUser appUser){
        this.testRepository.save(appUser);
    }
}
