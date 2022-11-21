package com.sprint2.book_store_webservice.service.impl;

import com.sprint2.book_store_webservice.model.Account;
import com.sprint2.book_store_webservice.model.AppRole;
import com.sprint2.book_store_webservice.model.AppUser;
import com.sprint2.book_store_webservice.repository.IAccountRepository;
import com.sprint2.book_store_webservice.repository.IAppUserRepository;
import com.sprint2.book_store_webservice.service.IAccountService;
import com.sprint2.book_store_webservice.service.IAppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

@Service
public class AccountService implements IAccountService {

    @Autowired
    private IAccountRepository accountRepository;

    @Autowired
    private IAppUserService appUserService;

    @Override
    public Account save(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public Account saveSocialAccount(String email, String name) {
        AppUser appUser = this.appUserService.save(
                new AppUser(null, name, null, email, null));

        String password = new BCryptPasswordEncoder(12).encode(String.valueOf(new Random().nextInt(100000000)));
        Set<AppRole> roles = new HashSet<>();
        roles.add(new AppRole(1L,"ROLE_USER",null));
        Account account = this.accountRepository.save(new Account(null,email, password,appUser,roles));
        return account;
    }

    @Override
    public Account findByUsername(String username) {
        return this.accountRepository.findByUsername(username);
    }
}
