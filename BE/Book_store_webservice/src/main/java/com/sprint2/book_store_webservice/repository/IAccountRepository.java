package com.sprint2.book_store_webservice.repository;

import com.sprint2.book_store_webservice.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAccountRepository extends JpaRepository<Account,Long> {

    Account findByUsername(String username);
}
