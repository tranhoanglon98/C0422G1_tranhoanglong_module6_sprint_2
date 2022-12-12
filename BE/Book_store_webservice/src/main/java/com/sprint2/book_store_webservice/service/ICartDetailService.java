package com.sprint2.book_store_webservice.service;

import com.sprint2.book_store_webservice.model.CartDetail;

import java.util.List;

public interface ICartDetailService {
    List<CartDetail> getCartDetail(String username);

    CartDetail findByAccountIdAndBookId(Long accountId, Long bookId);

    void save(CartDetail cartDetail);

    void delete(Long id);

    void update(List<CartDetail> cartDetails);
}

