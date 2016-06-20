package org.productfinding.controller;


import com.google.common.base.Charsets;
import com.google.common.hash.*;
import org.productfinding.entity.Utilisateur;
import org.productfinding.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;

@RestController
@RequestMapping("/utilisateur")
public class UtilisateurController {

    @Value("${my.salt}")
    String _salt;

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

    @RequestMapping(value="/new", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Utilisateur create(@RequestBody Utilisateur user) {
        Utilisateur checkUser = utilisateurRepository.findByUsername(user.getUsername());
        if (checkUser != null) {
            return null;
        } else {
            String pwd = user.getPassword();

            user.setPassword(this.hashPassword(pwd));
            user.setToken(this.hashToken(pwd));


            return utilisateurRepository.save(user);
        }
    }

    @RequestMapping(value="/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Utilisateur post(@RequestBody ArrayList<String> loggin) {
        Utilisateur user = new Utilisateur();

        ArrayList<Utilisateur> listUtilisateur = (ArrayList<Utilisateur>) utilisateurRepository.findAll();
        for (Utilisateur u : listUtilisateur) {
            if (u.getUsername().equals(loggin.get(0)) && u.getPassword().equals(this.hashPassword(loggin.get(1)))) {
                user = u;
                break;
            }
        }
        return user;
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

    private String hashPassword(String pwd) {

        HashFunction hf = Hashing.sha1();
        HashCode hc = hf.newHasher()
                .putString(pwd, Charsets.UTF_8)
                .putString(_salt, Charsets.UTF_8)
                .hash();
        return hc.toString();
    }


    private String hashToken(String pwd) {
        String hash = this.hashPassword(pwd);
        return this.hashPassword(hash + LocalDateTime.now());
    }

}
