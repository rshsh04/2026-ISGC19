package com.group4.api.repo;

import com.group4.api.model.Book;
import com.group4.api.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Repo extends JpaRepository<Book, Long> {
    List<Book> findByTitleContainingIgnoreCase(String query);
    List<Book> findByCategory(Category cate);
}