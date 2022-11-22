package com.sprint2.book_store_webservice.service;

import com.sprint2.book_store_webservice.model.projection.BookProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IBookService {

    Page<BookProjection> findByCategoryId(Long id, Pageable pageable);

    Page<BookProjection> findByTitle(String title,Pageable pageable);
}
