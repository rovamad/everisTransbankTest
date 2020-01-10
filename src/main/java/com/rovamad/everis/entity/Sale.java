package com.rovamad.everis.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Getter @Setter
@Table(name="sale")
@NamedQuery(name="Sale.findAll", query="SELECT s FROM Sale s")
public class Sale implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="sale_id")
    private long id;

    @Column(name="name")
    private String name;

    @Column(name="amount")
    private int amount;

    @Column(name="subtotal")
    private double subtotal;

    @Column(name="date")
    private String date;

}