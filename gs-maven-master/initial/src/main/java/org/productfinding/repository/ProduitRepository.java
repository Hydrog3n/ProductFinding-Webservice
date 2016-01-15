package org.productfinding.repository;

import org.productfinding.entity.Produit;
import org.springframework.data.repository.CrudRepository;


public interface ProduitRepository extends CrudRepository<Produit, Long> {
}
