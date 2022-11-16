package com.sprint2.book_store_webservice.service.impl;

import com.sprint2.book_store_webservice.model.Account;
import com.sprint2.book_store_webservice.model.AppRole;
import com.sprint2.book_store_webservice.repository.IAccountRepository;
import com.sprint2.book_store_webservice.repository.IAppRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private IAppRoleRepository appRoleRepository;

    @Autowired
    private IAccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account user = this.accountRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("Not found username in database");
        }

        List<AppRole> roleList = this.appRoleRepository.findByUsername(username);
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        for (AppRole role : roleList) {
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getRole()));
        }
        return new User(user.getUsername(), user.getPassword(), grantedAuthorities);
    }
}
