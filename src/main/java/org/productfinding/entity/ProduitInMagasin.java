package org.productfinding.entity;

import lombok.Data;
import org.hibernate.id.Assigned;
import org.springframework.context.annotation.Lazy;

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
    private String lattitude;

    @Column
    private String longitude;

    @Column
    private String ean;

    @Data
    @Embeddable
    public static class ProduitMagasinId implements Serializable {
        @ManyToOne
        private Produit produit;
        @ManyToOne
        private Magasin magasin;
    }
}
