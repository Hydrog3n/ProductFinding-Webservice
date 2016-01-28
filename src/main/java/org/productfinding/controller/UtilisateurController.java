package org.productfinding.controller;

import org.productfinding.entity.Utilisateur;
import org.productfinding.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/utilisateur")
public class UtilisateurController {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Iterable<Utilisateur> getAll() {
        return utilisateurRepository.findAll();
    }

    @RequestMapping(value="/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Utilisateur get(@PathVariable("id") Long id) {
        return utilisateurRepository.findOne(id);
    }

    @RequestMapping(value="/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Utilisateur create(@RequestBody Utilisateur user) {
        return utilisateurRepository.save(user);
    }

    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Iterable<Utilisateur> create(@RequestBody Iterable<Utilisateur> users) {
        return utilisateurRepository.save(users);
    }

    @RequestMapping(value="/{id}", method = RequestMethod.PATCH, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @Transactional
    public Utilisateur patch(@PathVariable("id") Long id, @RequestBody Utilisateur utilisateur) {
        Utilisateur databaseUtilisateur = utilisateurRepository.findOne(id);
        if (utilisateur.getUsername() != null) {
            databaseUtilisateur.setUsername(utilisateur.getUsername());
        }
        if (utilisateur.getPassword() != null) {
            databaseUtilisateur.setPassword(utilisateur.getPassword());
        }
        if (utilisateur.getFirstname() != null) {
            databaseUtilisateur.setFirstname(utilisateur.getFirstname());
        }
        if (utilisateur.getLastname() != null) {
            databaseUtilisateur.setLastname(utilisateur.getLastname());
        }
        return utilisateurRepository.save(databaseUtilisateur);
    }

    @RequestMapping(value="/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") Long id) {
        utilisateurRepository.delete(id);
    }

}
