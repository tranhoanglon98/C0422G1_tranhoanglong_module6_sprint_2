package com.sprint2.book_store_webservice.controller;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.sprint2.book_store_webservice.model.Account;
import com.sprint2.book_store_webservice.model.AppRole;
import com.sprint2.book_store_webservice.model.login.JwtUtility;
import com.sprint2.book_store_webservice.model.login.LoginRequest;
import com.sprint2.book_store_webservice.model.login.LoginResponse;
import com.sprint2.book_store_webservice.model.login.TokenDto;
import com.sprint2.book_store_webservice.service.IAccountService;
import com.sprint2.book_store_webservice.service.IAppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.User;
import org.springframework.social.facebook.api.impl.FacebookTemplate;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
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

    @Autowired
    private IAccountService accountService;

    @Autowired
    private IAppUserService appUserService;

    @Value("${google.clientId}")
    String googleClientId;


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

        Account account = this.accountService.findByUsername(loginRequest.get().getUsername());
        List<String> roles = userDetailService.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        String jwt = jwtUtility.generateJwtToken(loginRequest.get().getUsername());
        return new ResponseEntity<>(new LoginResponse(account.getId(),jwt, roles, loginRequest.get().getUsername(), account.getAppUser().getName()), HttpStatus.OK);
    }

    @PostMapping("/login/google")
    public ResponseEntity<?> loginWithGoogle(@RequestBody TokenDto tokenDto) throws IOException {
        NetHttpTransport transport = new NetHttpTransport();
        JacksonFactory jacksonFactory = JacksonFactory.getDefaultInstance();
        GoogleIdTokenVerifier.Builder verifier = new GoogleIdTokenVerifier.Builder(transport, jacksonFactory)
                .setAudience(Collections.singletonList(googleClientId));
        GoogleIdToken googleIdToken = GoogleIdToken.parse(verifier.getJsonFactory(), tokenDto.getValue());
        GoogleIdToken.Payload payload = googleIdToken.getPayload();
        String username = payload.getEmail();
        String token;
        Account account;
        token = this.jwtUtility.generateJwtToken(username);
        if (this.accountService.findByUsername(username) != null) {
            account = this.accountService.findByUsername(username);
        } else {
             account = this.accountService
                    .saveSocialAccount(username, (payload.get("given_name") + " " + payload.get("family_name")));
        }
        List<String> roles = new ArrayList<>();
        for (AppRole r: account.getAppRoles()){
            roles.add(r.getRole());
        }
        return new ResponseEntity<>(new LoginResponse(account.getId(),token,roles,username,account.getAppUser().getName()), HttpStatus.OK);
    }

    @PostMapping("login/facebook")
    public ResponseEntity<?> loginWithFacebook(@RequestBody TokenDto tokenDto) {
        Facebook facebook = new FacebookTemplate(tokenDto.getValue());
        String field[] = {"email", "name"};
        User user = facebook.fetchObject("me", User.class, field);
        String username = user.getEmail();
        String token;
        Account account;
        token = this.jwtUtility.generateJwtToken(username);
        if (this.accountService.findByUsername(username) != null) {
            account = this.accountService.findByUsername(username);
        } else {
            account = this.accountService
                    .saveSocialAccount(username, user.getName());
        }
        List<String> roles = new ArrayList<>();
        for (AppRole r: account.getAppRoles()){
            roles.add(r.getRole());
        }
        return new ResponseEntity<>(new LoginResponse(account.getId(),token,roles,username,account.getAppUser().getName()), HttpStatus.OK);
    }

}

