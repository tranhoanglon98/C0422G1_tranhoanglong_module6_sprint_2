package com.sprint2.book_store_webservice.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "varchar(60)")
    private String name;

    @OneToMany(mappedBy = "city")
    @JsonBackReference
    private List<Province> provinces;
}
