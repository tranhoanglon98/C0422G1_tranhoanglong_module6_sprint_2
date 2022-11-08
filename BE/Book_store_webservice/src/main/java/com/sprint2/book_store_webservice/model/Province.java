package com.sprint2.book_store_webservice.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Province {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "varchar(60)")
    private String name;

    @ManyToOne
    @JoinColumn(name = "city_id",referencedColumnName = "id")
    private City city;
}
