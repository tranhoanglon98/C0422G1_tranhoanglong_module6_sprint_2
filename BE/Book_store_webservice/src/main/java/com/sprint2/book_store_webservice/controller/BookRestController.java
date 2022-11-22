package com.sprint2.book_store_webservice.controller;

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
@RequestMapping("/api")
public class BookRestController {

    @Autowired
    private IBookService bookService;

    @Autowired
    private ICategoryService categoryService;

    @GetMapping("/public/book")
    public ResponseEntity<Page<BookProjection>> getBook(@RequestParam Optional<String> bookTitle,
                                                        @RequestParam Optional<Long> categoryId,
                                                        @PageableDefault Pageable pageable){
        Page<BookProjection> bookPage;
        if (categoryId.isPresent()){
            bookPage = bookService.findByCategoryId(categoryId.get(), pageable);
        }else {
            if (bookTitle.isPresent()){
                bookPage = bookService.findByTitle(bookTitle.get(),pageable);
            }else {
                bookPage = bookService.findByTitle("",pageable);
            }
        }
        return  new ResponseEntity<>(bookPage,HttpStatus.OK);
    }

    @GetMapping("/public/category")
    public ResponseEntity<List<Category>> getListCategory(){
        return new ResponseEntity<>(categoryService.getAll(),HttpStatus.OK);
    }
}
