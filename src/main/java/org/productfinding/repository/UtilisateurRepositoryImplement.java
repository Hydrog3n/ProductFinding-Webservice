package org.productfinding.repository;

import org.productfinding.entity.Utilisateur;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by hydrog3n on 04/06/2016.
 */
public class UtilisateurRepositoryImplement implements UtilisateurRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager;

    public List<Utilisateur> findManyByUsername(String username) {
        List<Utilisateur> utilisateurs = (List<Utilisateur>) this.entityManager
                .createQuery(String.format("select u from Utilisateur u where u.username like '%s'", username))
                .getResultList();
        return utilisateurs;
    }

    @Override
    public Utilisateur findByUsername(String username) {
        Utilisateur utilisateur = (Utilisateur) entityManager
                .createQuery(String.format("select u from Utilisateur u where u.username like '%s'", username))
                .getSingleResult();
        return utilisateur;
    }
}
