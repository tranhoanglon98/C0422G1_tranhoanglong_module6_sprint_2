package com.sprint2.book_store_webservice.repository;

import com.sprint2.book_store_webservice.model.CartDetail;
import com.sprint2.book_store_webservice.model.projection.CartDetailProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface ICartDetailRepository extends JpaRepository<CartDetail, Long> {

    @Query(value = "SELECT \n" +
            "    cd.id, cd.quantity, cd.status, cd.account_id, cd.book_id\n" +
            "FROM\n" +
            "    cart_detail AS cd\n" +
            "        JOIN\n" +
            "    `account` AS a ON a.id = cd.account_id\n" +
            "WHERE\n" +
            "    a.username = ?1 AND cd.status = true \n" +
            "ORDER BY cd.id DESC ", nativeQuery = true)
    List<CartDetail> findByUsername(String username);


    CartDetail findByAccount_IdAndBook_IdAndStatus(Long accountId, Long bookId, Boolean status);

    @Modifying
    @Query(value = "update cart_detail set status = false where id = ?1 ", nativeQuery = true)
    void changeStatus(Long id);
}
