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
            "    b.image_url AS imageUrl,\n" +
            "    b.title AS title,\n" +
            "    b.price AS price,\n" +
            "    SUM(i.quantity) AS sold\n" +
            "FROM\n" +
            "    book AS b\n" +
            "        JOIN book_detail as bd on b.id = bd.book_id join category as c \n" +
            "        on c.id = bd.category_id\n" +
            "        left join invoice as i on i.book_id = b.id\n" +
            "        where c.name = ?1\n" +
            "        group by b.id ",nativeQuery = true,
            countQuery = "SELECT \n" +
                    "    b.id AS id,\n" +
                    "    b.image_url AS imageUrl,\n" +
                    "    b.title AS title,\n" +
                    "    b.price AS price,\n" +
                    "    SUM(i.quantity) AS sold\n" +
                    "FROM\n" +
                    "    book AS b\n" +
                    "        JOIN book_detail as bd on b.id = bd.book_id join category as c \n" +
                    "        on c.id = bd.category_id\n" +
                    "        left join invoice as i on i.book_id = b.id\n" +
                    "        where c.name = ?1\n" +
                    "        group by b.id ")
    Page<BookProjection> findByCategory(String category, Pageable pageable);


    @Query(value = "SELECT \n" +
            "    b.id AS id,\n" +
            "    b.image_url AS imageUrl,\n" +
            "    b.title AS title,\n" +
            "    b.price AS price,\n" +
            "    SUM(i.quantity) AS sold\n" +
            "FROM\n" +
            "    book AS b\n" +
            "        LEFT JOIN\n" +
            "    invoice AS i ON b.id = i.book_id\n" +
            "        JOIN\n" +
            "    author AS a ON a.id = b.author_id\n" +
            "WHERE\n" +
            "    a.name LIKE CONCAT('%', ?1, '%')\n" +
            "        OR b.title LIKE CONCAT('%', ?1, '%')\n" +
            "GROUP BY b.id\n" +
            "ORDER BY b.image_url DESC ",nativeQuery = true,
    countQuery = "SELECT \n" +
            "    b.id AS id,\n" +
            "    b.image_url AS imageUrl,\n" +
            "    b.title AS title,\n" +
            "    b.price AS price,\n" +
            "    SUM(i.quantity) AS sold\n" +
            "FROM\n" +
            "    book AS b\n" +
            "        LEFT JOIN\n" +
            "    invoice AS i ON b.id = i.book_id\n" +
            "        JOIN\n" +
            "    author AS a ON a.id = b.author_id\n" +
            "WHERE\n" +
            "    a.name LIKE CONCAT('%', ?1, '%')\n" +
            "        OR b.title LIKE CONCAT('%', ?1, '%')\n" +
            "GROUP BY b.id\n" +
            "ORDER BY b.image_url ASC ")
    Page<BookProjection> findByTitleOrAuthor(String title, Pageable pageable);


    @Query(value = "SELECT \n" +
            "    b.id AS id,\n" +
            "    b.image_url AS imageUrl,\n" +
            "    b.title AS title,\n" +
            "    b.price AS price,\n" +
            "    SUM(i.quantity) AS sold\n" +
            "FROM\n" +
            "    book AS b\n" +
            "        JOIN\n" +
            "    invoice AS i ON b.id = i.book_id\n" +
            "GROUP BY b.id\n" +
            "ORDER BY sold DESC\n" +
            "LIMIT 5 ", nativeQuery = true)
    List<BookProjection> getBestseller();
}
