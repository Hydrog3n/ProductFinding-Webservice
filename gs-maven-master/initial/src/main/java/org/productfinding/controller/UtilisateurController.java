package org.productfinding.controller;

import org.productfinding.entity.Utilisateur;
import org.productfinding.repository.UtilisateurRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

/**
 * Created by alehmann on 15/01/2016.
 */
@RestController
@RequestMapping("/user")
public class UtilisateurController {

    private UtilisateurRepository userRepositoy;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Iterable<Utilisateur> getAll() {
        return userRepositoy.findAll();
    }

    @RequestMapping(value="/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Utilisateur get(@PathVariable("id") Long id) {
        return userRepositoy.findOne(id);
    }

    @RequestMapping(value="/{id}", method = RequestMethod.PATCH, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @Transactional
    public Utilisateur patch(@PathVariable("id") Long id, @RequestBody Utilisateur utilisateur) {
        Utilisateur databaseUtilisateur = userRepositoy.findOne(id);
        if (utilisateur.getUsername() != null) {
            databaseUtilisateur.setUsername(utilisateur.getUsername());
        }
        if (utilisateur.getPassword() != null) {
            databaseUtilisateur.setPassword(utilisateur.getPassword());
        }
        if (utilisateur.getFirstName() != null) {
            databaseUtilisateur.setFirstName(utilisateur.getFirstName());
        }
        if (utilisateur.getLastName() != null) {
            databaseUtilisateur.setLastName(utilisateur.getLastName());
        }
        return userRepositoy.save(databaseUtilisateur);
    }

    @RequestMapping(value="/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") Long id) {
        userRepositoy.delete(id);
    }

    public UtilisateurRepository getUserRepositoy() {
        return userRepositoy;
    }

    public void setUserRepositoy(UtilisateurRepository userRepositoy) {
        this.userRepositoy = userRepositoy;
    }
}
