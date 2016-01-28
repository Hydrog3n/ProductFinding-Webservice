package org.productfinding.controller;
import org.productfinding.entity.Produit;
import org.productfinding.repository.ProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

/**
 * Created by fteychene on 1/15/16.
 */
@RestController
@RequestMapping("/adress")
public class ProduitController {

    @Autowired
    private ProduitRepository repositoy;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Iterable<Produit> getAll() {
        return repositoy.findAll();
    }

    @RequestMapping(value="/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Produit get(@PathVariable("id") Long id) {
        return repositoy.findOne(id);
    }

    @RequestMapping(value="/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Produit create(@RequestBody Produit user) {
        return repositoy.save(user);
    }

    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Iterable<Produit> create(@RequestBody Iterable<Produit> user) {
        return repositoy.save(user);
    }

    @RequestMapping(value="/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Produit update(@PathVariable("id") Long id, @RequestBody Produit user) {
        user.setId(id);
        return repositoy.save(user);
    }

    @RequestMapping(value="/{id}", method = RequestMethod.PATCH, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @Transactional
    public Produit patch(@PathVariable("id") Long id, @RequestBody Produit produit) {
        Produit databaseAddress  = repositoy.findOne(id);
        if (produit.getDescriptif() != null) {
            databaseAddress.setDescriptif(produit.getDescriptif());
        }
        if (produit.getMarque() != null) {
            databaseAddress.setMarque(produit.getMarque());
        }
        return repositoy.save(databaseAddress);
    }

    @RequestMapping(value="/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathParam("id") Long id) {
        repositoy.delete(id);
    }

    public ProduitRepository getRepositoy() {
        return repositoy;
    }

    public void setRepositoy(ProduitRepository repositoy) {
        this.repositoy = repositoy;
    }
}
