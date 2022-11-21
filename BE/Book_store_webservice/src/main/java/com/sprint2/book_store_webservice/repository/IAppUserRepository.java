package com.sprint2.book_store_webservice.repository;

import com.sprint2.book_store_webservice.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAppUserRepository extends JpaRepository<AppUser,Long> {
}
