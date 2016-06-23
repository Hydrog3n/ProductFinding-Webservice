package org.productfinding.repository;

import org.productfinding.entity.Utilisateur;
import org.springframework.data.repository.CrudRepository;

public interface UtilisateurRepository extends CrudRepository<Utilisateur, Long> {

    public Utilisateur findByUsername(String username);

    public Utilisateur findOneByToken(String token);
}
