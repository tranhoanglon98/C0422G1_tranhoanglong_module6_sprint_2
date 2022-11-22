package com.sprint2.book_store_webservice.service.impl;

import com.sprint2.book_store_webservice.model.Category;
import com.sprint2.book_store_webservice.repository.ICategoryRepository;
import com.sprint2.book_store_webservice.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService implements ICategoryService {

    @Autowired
    private ICategoryRepository categoryRepository;

    @Override
    public List<Category> getAll() {
        return categoryRepository.findAll();
    }
}
