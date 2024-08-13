package com.javacurd.springboot.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "addresses")
public class Address {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @Column(name = "streets")
    private String street;

    @Column(name = "city")
    private String city;

    @Column(name = "zip_code")
    private String zipcode;

    @OneToOne(mappedBy = "address")
    @JsonIgnore
    @JsonBackReference
    private Employee employee;

}
