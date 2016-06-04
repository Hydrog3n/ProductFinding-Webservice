package org.productfinding.repository.Utilisateur;

import org.productfinding.entity.Utilisateur;

import java.util.List;

/**
 * Created by hydrog3n on 04/06/2016.
 */
public interface UtilisateurRepositoryCustom {
    public Utilisateur findByUsername(String username);
    public List<Utilisateur> findManyByUsername(String username);
}
