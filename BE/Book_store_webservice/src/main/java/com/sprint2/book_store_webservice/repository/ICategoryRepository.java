package com.sprint2.book_store_webservice.repository;

import com.sprint2.book_store_webservice.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICategoryRepository extends JpaRepository<Category,Long> {
}
