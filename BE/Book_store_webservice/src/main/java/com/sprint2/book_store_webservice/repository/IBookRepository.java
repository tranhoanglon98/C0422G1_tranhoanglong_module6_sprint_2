package com.sprint2.book_store_webservice.repository;

import com.sprint2.book_store_webservice.model.Book;
import com.sprint2.book_store_webservice.model.projection.BookProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IBookRepository extends JpaRepository<Book,Long> {

    @Query(value = "SELECT \n" +
            "    b.id AS id,\n" +
            "    b.title AS title,\n" +
            "    b.price AS price,\n" +
            "    b.image_url AS url,\n" +
            "    COUNT(bd.book_id) AS sold\n" +
            "FROM\n" +
            "    book_store.book AS b\n" +
            "        JOIN\n" +
            "    book_detail AS bd ON bd.book_id = b.id\n" +
            "    join category as c on c.id = bd.category_id\n" +
            "WHERE\n" +
            "    c.name = ?1 \n" +
            "GROUP BY b.id ",nativeQuery = true,
            countQuery = "SELECT \n" +
                    "    b.id AS id,\n" +
                    "    b.title AS title,\n" +
                    "    b.price AS price,\n" +
                    "    b.image_url AS url,\n" +
                    "    COUNT(bd.book_id) AS sold\n" +
                    "FROM\n" +
                    "    book_store.book AS b\n" +
                    "        JOIN\n" +
                    "    book_detail AS bd ON bd.book_id = b.id\n" +
                    "    join category as c on c.id = bd.category_id\n" +
                    "WHERE\n" +
                    "    c.name = ?1 \n" +
                    "GROUP BY b.id ")
    Page<BookProjection> findByCategory(String category, Pageable pageable);


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
            "        JOIN\n" +
            "    author AS a ON a.id = b.author_id\n" +
            "WHERE\n" +
            "    b.title LIKE CONCAT('%', ?1, '%')\n" +
            "        OR a.name LIKE CONCAT('%', ?1, '%')\n" +
            "GROUP BY b.id\n" +
            "ORDER BY b.title DESC ",nativeQuery = true,
    countQuery = "SELECT \n" +
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
            "        JOIN\n" +
            "    author AS a ON a.id = b.author_id\n" +
            "WHERE\n" +
            "    b.title LIKE CONCAT('%', ?1, '%')\n" +
            "        OR a.name LIKE CONCAT('%', ?1, '%')\n" +
            "GROUP BY b.id\n" +
            "ORDER BY b.image_url DESC ")
    Page<BookProjection> findByTitleOrAuthor(String title, Pageable pageable);


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
