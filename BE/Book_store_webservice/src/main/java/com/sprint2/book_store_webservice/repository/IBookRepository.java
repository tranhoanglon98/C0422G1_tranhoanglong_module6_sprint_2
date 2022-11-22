package com.sprint2.book_store_webservice.repository;

import com.sprint2.book_store_webservice.model.Book;
import com.sprint2.book_store_webservice.model.projection.BookProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IBookRepository extends JpaRepository<Book,Long> {

    @Query(value = "SELECT " +
            "    b.id AS id, " +
            "    b.title AS title, " +
            "    b.image_url AS url, " +
            "    b.price AS price " +
            "FROM " +
            "    book AS b " +
            "        JOIN " +
            "    book_detail AS bd ON bd.book_id = b.id " +
            "        JOIN " +
            "    category AS c ON c.id = bd.category_id " +
            "WHERE " +
            "    c.id = ?1 ",nativeQuery = true,
            countQuery = "SELECT " +
                    "    b.id AS id, " +
                    "    b.title AS title, " +
                    "    b.image_url AS url, " +
                    "    b.price AS price " +
                    "FROM " +
                    "    book AS b " +
                    "        JOIN " +
                    "    book_detail AS bd ON bd.book_id = b.id" +
                    "        JOIN " +
                    "    category AS c ON c.id = bd.category_id " +
                    "WHERE " +
                    "    c.id = ?1 ")
    Page<BookProjection> findByCategoryId(Long id, Pageable pageable);


    @Query(value = "SELECT " +
            "    b.id AS id, " +
            "    b.title AS title, " +
            "    b.image_url AS url, " +
            "    b.price AS price " +
            "FROM " +
            "    book AS b " +
            "WHERE " +
            "    b.title LIKE concat('%',?1,'%')",nativeQuery = true,
    countQuery = "select * from book where title like concat('%',?1,'%') ")
    Page<BookProjection> findByTitle(String title, Pageable pageable);
}
