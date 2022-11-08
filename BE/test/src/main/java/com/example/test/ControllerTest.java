package com.example.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControllerTest {

    @Autowired
    private service s;

    @PostMapping("")
    public ResponseEntity<String> test(@RequestBody AppUser appUser){
        s.save(appUser);
        return new ResponseEntity<>("ok", HttpStatus.OK);
    }
}
