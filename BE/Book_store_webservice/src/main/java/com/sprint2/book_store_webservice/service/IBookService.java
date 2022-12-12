package com.sprint2.book_store_webservice.service;

import com.sprint2.book_store_webservice.model.Book;
import com.sprint2.book_store_webservice.model.projection.BookProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IBookService {

    Page<BookProjection> findByCategory(String category, Pageable pageable);

    Page<BookProjection> findByTitleOrAuthor(String title, Pageable pageable);

    List<BookProjection> getBestseller();

    Book findById(Long id);
}
