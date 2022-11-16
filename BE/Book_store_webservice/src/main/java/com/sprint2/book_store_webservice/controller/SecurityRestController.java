package com.sprint2.book_store_webservice.controller;

import com.sprint2.book_store_webservice.model.login.JwtUtility;
import com.sprint2.book_store_webservice.model.login.LoginRequest;
import com.sprint2.book_store_webservice.model.login.LoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/public")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class SecurityRestController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtility jwtUtility;


    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody Optional<LoginRequest> loginRequest) {

        if (!loginRequest.isPresent()) {
            return new ResponseEntity<>("Không được để trống tài khoản, mật khẩu", HttpStatus.BAD_REQUEST);
        }
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.get().getUsername(), loginRequest.get().getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails userDetailService = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();


        List<String> roles = userDetailService.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        String jwt = jwtUtility.generateJwtToken(loginRequest.get().getUsername());
        return new ResponseEntity<>(new LoginResponse(jwt, roles, loginRequest.get().getUsername()), HttpStatus.OK);
    }

}
