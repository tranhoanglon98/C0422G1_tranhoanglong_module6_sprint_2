package com.sprint2.book_store_webservice.repository;

import com.sprint2.book_store_webservice.model.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IAppRoleRepository extends JpaRepository<AppRole,Long> {

    @Query(value = "SELECT " +
            "    * " +
            "FROM " +
            "    app_role AS r " +
            "        JOIN " +
            "    account_role AS ar ON r.id = ar.role_id " +
            "        JOIN " +
            "    account AS a ON a.id = ar.account_id " +
            "WHERE " +
            "    a.username = :username", nativeQuery = true)
    List<AppRole> findByUsername(@Param("username") String username);
}
