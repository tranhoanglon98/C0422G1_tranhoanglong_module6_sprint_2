package com.sprint2.book_store_webservice.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "varchar(200)")
    private String title;

    @ManyToOne
    @JoinColumn(name = "publisher_id",referencedColumnName = "id")
    private Publisher publisher;

    private Double price;

    private Integer totalPages;

    private Double width;

    private Double height;

    @Column(columnDefinition = "text")
    private String imageUrl;

    @Column(columnDefinition = "text")
    private String summary;

    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "author_id",referencedColumnName = "id")
    private Author author;

    @ManyToMany
    @JsonBackReference(value = "category")
    @JoinTable(name = "book_detail",joinColumns = @JoinColumn(name = "book_id"),
    inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Category> categories;

}
