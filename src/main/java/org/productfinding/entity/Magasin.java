package org.productfinding.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

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

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public List<ProduitInMagasin> getListProduit() {
        return listProduit;
    }

    public void setListProduit(List<ProduitInMagasin> listProduit) {
        this.listProduit = listProduit;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Magasin magasin = (Magasin) o;

        if (id != null ? !id.equals(magasin.id) : magasin.id != null) return false;
        if (name != null ? !name.equals(magasin.name) : magasin.name != null) return false;
        if (address != null ? !address.equals(magasin.address) : magasin.address != null) return false;
        if (ville != null ? !ville.equals(magasin.ville) : magasin.ville != null) return false;
        return cp != null ? cp.equals(magasin.cp) : magasin.cp == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (ville != null ? ville.hashCode() : 0);
        result = 31 * result + (cp != null ? cp.hashCode() : 0);
        return result;
    }
}
