package com.group4.api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "title cannot be empty")
    @Column(nullable = false)
    private String title;

    @Size(max = 500, message = "Description maximum length is 500 characters")
    @Column(length = 500)
    private String description;
    private String publishedYear;

    @NotBlank(message = "Author cannot be empty")
    @Column(nullable = false)
    private String author;

    @Enumerated(EnumType.STRING)
    private Category category;

    public Book() {}


    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public String getPublishedYear() { return publishedYear; }
    public String getAuthor() { return author; }
    public Category getCategory() { return category; }

    public void setTitle(String title) { this.title = title; }
    public void setDescription(String description) { this.description = description; }
    public void setPublishedYear(String publishedYear) { this.publishedYear = publishedYear; }
    public void setAuthor(String author) { this.author = author; }
    public void setCategory(Category cate) { this.category = cate; }



}