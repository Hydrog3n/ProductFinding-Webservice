package org.productfinding.repository;

import org.productfinding.entity.Produit;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface ProduitRepository extends CrudRepository<Produit, Long> {
    @Query("select p from Produit p, ProduitInMagasin pm where pm.id.produit.id = p.id and pm.id.magasin.id = :id_mag and p.descriptif like :search")
    public List<Produit> findByDescriptif(@Param("search") String search, @Param("id_mag") Long id_mag);

    public Produit findOneByEan(String ean);
}
