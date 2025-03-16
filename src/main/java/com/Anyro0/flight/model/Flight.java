package com.Anyro0.flight.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "FLIGHTS")
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String date; // Flight date 

    @Column(nullable = false, length = 100)
    private String departure; // Flight departure location

    @Column(nullable = false, length = 100)
    private String destination; // Flight destination

    @Column(nullable = false, precision = 10, scale = 2)
    private String price; // Flight price

}
