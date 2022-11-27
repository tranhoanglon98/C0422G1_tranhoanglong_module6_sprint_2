package com.sprint2.book_store_webservice.repository;

import com.sprint2.book_store_webservice.model.Book;
import com.sprint2.book_store_webservice.model.projection.BookProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IBookRepository extends JpaRepository<Book,Long> {

    @Query(value = "SELECT " +
            "    b.id AS id, " +
            "    b.title AS title, " +
            "    b.image_url AS url, " +
            "    b.price AS price," +
            "    COUNT(id.book_id) as sold " +
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
                    "    b.price AS price," +
                    "    COUNT(id.book_id) as sold " +
                    "FROM " +
                    "    book AS b " +
                    "        JOIN " +
                    "    book_detail AS bd ON bd.book_id = b.id" +
                    "        JOIN " +
                    "    category AS c ON c.id = bd.category_id " +
                    "WHERE " +
                    "    c.id = ?1 ")
    Page<BookProjection> findByCategoryId(Long id, Pageable pageable);


    @Query(value = "SELECT \n" +
            "    b.id AS id,\n" +
            "    b.title AS title,\n" +
            "    b.image_url AS url,\n" +
            "    b.price AS price,\n" +
            "    COUNT(id.book_id) AS sold\n" +
            "FROM\n" +
            "    book AS b\n" +
            "        LEFT JOIN\n" +
            "    invoice_detail AS id ON id.book_id = b.id\n" +
            "        LEFT JOIN\n" +
            "    invoice AS i ON i.id = id.invoice_id\n" +
            "WHERE\n" +
            "    b.title LIKE CONCAT('%',?1, '%')\n" +
            "GROUP BY b.id\n" +
            "ORDER BY b.image_url DESC ",nativeQuery = true,
    countQuery = "select * from book where title like concat('%',?1,'%') ")
    Page<BookProjection> findByTitle(String title, Pageable pageable);


    @Query(value = "SELECT " +
            "    b.id AS id, " +
            "    b.title AS title, " +
            "    b.image_url AS url, " +
            "    b.price AS price, " +
            "    COUNT(id.book_id) AS sold " +
            "FROM " +
            "    book AS b " +
            "        JOIN " +
            "    invoice_detail AS id ON id.book_id = b.id " +
            "        JOIN " +
            "    invoice AS i ON i.id = id.invoice_id " +
            "GROUP BY b.id " +
            "ORDER BY sold DESC " +
            "LIMIT 5 ", nativeQuery = true)
    List<BookProjection> getBestseller();
}
