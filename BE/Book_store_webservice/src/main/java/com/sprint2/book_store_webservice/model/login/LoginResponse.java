package com.sprint2.book_store_webservice.model.login;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {

    private Long id;

    private String jwt;

    private List<String> roles;

    private String username;

    private String name;
}
