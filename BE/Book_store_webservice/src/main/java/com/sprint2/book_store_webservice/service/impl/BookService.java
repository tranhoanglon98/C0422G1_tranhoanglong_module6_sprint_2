package com.sprint2.book_store_webservice.service.impl;

import com.sprint2.book_store_webservice.model.projection.BookProjection;
import com.sprint2.book_store_webservice.repository.IBookRepository;
import com.sprint2.book_store_webservice.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService implements IBookService {

    @Autowired
    private IBookRepository bookRepository;

    @Override
    public Page<BookProjection> findByCategoryId(Long id, Pageable pageable) {
        return this.bookRepository.findByCategoryId(id,pageable);
    }

    @Override
    public Page<BookProjection> findByTitle(String title, Pageable pageable) {
        return this.bookRepository.findByTitle(title,pageable);
    }

    @Override
    public List<BookProjection> getBestseller() {
        return bookRepository.getBestseller();
    }
}
