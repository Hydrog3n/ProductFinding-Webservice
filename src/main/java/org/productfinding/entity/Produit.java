package org.productfinding.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Produit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String descriptif;

    @Column(nullable = false)
    private String marque;

    @Column
    private String imageUrl;

    @OneToMany(mappedBy = "id.produit", cascade = CascadeType.ALL)
    private List<ProduitInMagasin> listMagasin;
}
