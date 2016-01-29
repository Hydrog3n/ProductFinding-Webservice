package org.productfinding.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Produit {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String descriptif;

    @Column(nullable = false)
    private String marque;

    public String getDescriptif() {
        return this.descriptif;
    }

    public String getMarque() {
        return this.marque;
    }

    public void setId(Long newId) { this.id = newId; }

    public void setDescriptif(String newDescriptif) { this.descriptif = newDescriptif; }

    public void setMarque(String newMarque) { this.marque = newMarque; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Produit produit = (Produit) o;

        if (id != null ? !id.equals(produit.id) : produit.id != null) return false;
        if (descriptif != null ? !descriptif.equals(produit.descriptif) : produit.descriptif != null) return false;
        return marque != null ? marque.equals(produit.marque) : produit.marque == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (descriptif != null ? descriptif.hashCode() : 0);
        result = 31 * result + (marque != null ? marque.hashCode() : 0);
        return result;
    }
}
