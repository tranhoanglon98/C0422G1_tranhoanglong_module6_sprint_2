package com.sprint2.book_store_webservice.controller;

import com.sprint2.book_store_webservice.model.Book;
import com.sprint2.book_store_webservice.model.Category;
import com.sprint2.book_store_webservice.model.projection.BookProjection;
import com.sprint2.book_store_webservice.service.IBookService;
import com.sprint2.book_store_webservice.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/public/book")
public class BookRestController {

    @Autowired
    private IBookService bookService;

    @Autowired
    private ICategoryService categoryService;

    @GetMapping("")
    public ResponseEntity<Page<BookProjection>> getBook(@RequestParam Optional<String> searchValue,
                                                        @RequestParam Optional<String> category,
                                                        @PageableDefault Pageable pageable) {
        Page<BookProjection> bookPage;
        if (!category.get().equals("")) {
            bookPage = bookService.findByCategory(category.get(), pageable);
        } else {
            if (searchValue.isPresent()) {
                bookPage = bookService.findByTitleOrAuthor(searchValue.get(), pageable);
            } else {
                bookPage = bookService.findByTitleOrAuthor("", pageable);
            }
        }
        if (bookPage.hasContent()) {
            return new ResponseEntity<>(bookPage, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/category")
    public ResponseEntity<List<Category>> getListCategory() {
        return new ResponseEntity<>(categoryService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/bestseller")
    public ResponseEntity<List<BookProjection>> getBestseller() {
        return new ResponseEntity<>(this.bookService.getBestseller(), HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Book> getBookDetail(@PathVariable Long id){
        Book book = this.bookService.findById(id);
        if (book != null){
            return new ResponseEntity<>(book,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
