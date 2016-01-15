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
}
