package org.productfinding.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Utilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String username;

    private String password;
    private String firstname;
    private String lastname;
}
