package com.sprint2.book_store_webservice.service.impl;

import com.sprint2.book_store_webservice.model.AppUser;
import com.sprint2.book_store_webservice.repository.IAppUserRepository;
import com.sprint2.book_store_webservice.service.IAppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppUserService implements IAppUserService {

    @Autowired
    private IAppUserRepository appUserRepository;

    @Override
    public AppUser save(AppUser appUser) {
       return this.appUserRepository.save(appUser);
    }
}
