package com.sprint2.book_store_webservice.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String phone;

    private String email;

    @OneToOne
    @JoinColumn(name = "address_id",referencedColumnName = "id")
    private Address address;
}
