package org.productfinding.repository;

import org.productfinding.entity.Utilisateur;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by alehmann on 15/01/2016.
 */
public interface UtilisateurRepository extends CrudRepository<Utilisateur, Long> {
}
