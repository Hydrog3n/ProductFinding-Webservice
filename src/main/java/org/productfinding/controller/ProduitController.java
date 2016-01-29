package org.productfinding.controller;
import org.productfinding.entity.Produit;
import org.productfinding.repository.ProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@RestController
@RequestMapping("/produit")
public class ProduitController {

    @Autowired
    private ProduitRepository produitRepository;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Iterable<Produit> getAll() {
        return produitRepository.findAll();
    }

    @RequestMapping(value="/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Produit get(@PathVariable("id") Long id) {
        return produitRepository.findOne(id);
    }

    @RequestMapping(value="/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Produit create(@RequestBody Produit produit) {
        return produitRepository.save(produit);
    }

    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Iterable<Produit> create(@RequestBody Iterable<Produit> produit) {
        return produitRepository.save(produit);
    }

    @RequestMapping(value="/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Produit update(@PathVariable("id") Long id, @RequestBody Produit produit) {
        produit.setId(id);
        return produitRepository.save(produit);
    }

    @RequestMapping(value="/{id}", method = RequestMethod.PATCH, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @Transactional
    public Produit patch(@PathVariable("id") Long id, @RequestBody Produit produit) {
        Produit databaseAddress  = produitRepository.findOne(id);
        if (produit.getDescriptif() != null) {
            databaseAddress.setDescriptif(produit.getDescriptif());
        }
        if (produit.getMarque() != null) {
            databaseAddress.setMarque(produit.getMarque());
        }
        return produitRepository.save(databaseAddress);
    }

    @RequestMapping(value="/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathParam("id") Long id) {
        produitRepository.delete(id);
    }

    public ProduitRepository getRepositoy() {
        return produitRepository;
    }

    public void setRepositoy(ProduitRepository repositoy) {
        this.produitRepository = repositoy;
    }
}