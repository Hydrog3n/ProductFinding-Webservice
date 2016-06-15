package org.productfinding.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Magasin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String ville;

    @Column(nullable = false)
    private String cp;

    @Column
    private String logoUrl;

    @OneToMany(mappedBy = "id.magasin", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<ProduitInMagasin> listProduit;

}
