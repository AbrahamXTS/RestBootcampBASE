package com.bancobase.bootcamp.schemas;

import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@Getter
@Setter
@Entity
@Table(name = "customers")
public class CustomerSchema {
    @Id
    @Column(name = "customer_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "curp", nullable = false, unique = true)
    private String curp;

    @Column(name = "gender", nullable = false)
    private String gender;

    @Column(name = "birthdate", nullable = false)
    private Date birthdate;

    @OneToMany(mappedBy = "customer")
    private List<AccountSchema> accounts;
}
