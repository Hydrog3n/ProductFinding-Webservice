package org.productfinding.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
public class ProduitInMagasin {

    @EmbeddedId
    private ProduitMagasinId id;

    @Column
    private Float prix;

    @Column
    private double latitude;

    @Column
    private double longitude;

    @Data
    @Embeddable
    public static class ProduitMagasinId implements Serializable {
        @ManyToOne
        private Produit produit;
        @ManyToOne
        private Magasin magasin;
    }
}
