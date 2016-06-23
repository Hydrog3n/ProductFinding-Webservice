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

    @Column
    private String ean;

    @Transient
    private float prix;

    @Transient
    private double longitude;

    @Transient
    private double latitude;

    @OneToMany(mappedBy = "id.produit", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<ProduitInMagasin> listMagasin;
}
