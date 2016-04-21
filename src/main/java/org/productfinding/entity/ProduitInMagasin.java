package org.productfinding.entity;

import org.hibernate.id.Assigned;
import org.springframework.context.annotation.Lazy;

import javax.persistence.*;
import java.io.Serializable;

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

    public ProduitMagasinId getId() {
        return id;
    }

    public void setId(ProduitMagasinId id) {
        this.id = id;
    }

    public Float getPrix() {
        return prix;
    }

    public void setPrix(Float prix) {
        this.prix = prix;
    }

    public String getLattitude() {
        return lattitude;
    }

    public void setLattitude(String lattitude) {
        this.lattitude = lattitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProduitInMagasin that = (ProduitInMagasin) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (prix != null ? !prix.equals(that.prix) : that.prix != null) return false;
        if (lattitude != null ? !lattitude.equals(that.lattitude) : that.lattitude != null) return false;
        return longitude != null ? longitude.equals(that.longitude) : that.longitude == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (prix != null ? prix.hashCode() : 0);
        result = 31 * result + (lattitude != null ? lattitude.hashCode() : 0);
        result = 31 * result + (longitude != null ? longitude.hashCode() : 0);
        return result;
    }

    @Embeddable
    public static class ProduitMagasinId implements Serializable {
        @ManyToOne
        private Produit produit;
        @ManyToOne
        private Magasin magasin;

        public Produit getProduit() {
            return produit;
        }

        public void setProduit(Produit produit) {
            this.produit = produit;
        }

        public Magasin getMagasin() {
            return magasin;
        }

        public void setMagasin(Magasin magasin) {
            this.magasin = magasin;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            ProduitMagasinId that = (ProduitMagasinId) o;

            if (produit != null ? !produit.equals(that.produit) : that.produit != null) return false;
            return magasin != null ? magasin.equals(that.magasin) : that.magasin == null;

        }

        @Override
        public int hashCode() {
            int result = produit != null ? produit.hashCode() : 0;
            result = 31 * result + (magasin != null ? magasin.hashCode() : 0);
            return result;
        }
    }
}
