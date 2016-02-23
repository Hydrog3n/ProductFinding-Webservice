package org.productfinding.controller;

import org.productfinding.entity.Magasin;
import org.productfinding.entity.Produit;
import org.productfinding.repository.MagasinRepository;
import org.productfinding.repository.ProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/magasin")
public class MagasinControler {
    @Autowired
    private MagasinRepository repository;
    @Autowired
    private ProduitRepository produitRepository;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Iterable<Magasin> getAll() {
        return repository.findAll();
    }

    @RequestMapping(value="/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Magasin get(@PathVariable("id") Long id) {
        return repository.findOne(id);
    }

    @RequestMapping(value="/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Magasin create(@RequestBody Magasin magasin) {
        for (Produit produit : magasin.getListProduit()) {
            produitRepository.save(produit);

        }
        return repository.save(magasin);
    }

    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Iterable<Magasin> create(@RequestBody Iterable<Magasin> magasin) {
        return repository.save(magasin);
    }

    @RequestMapping(value="/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Magasin update(@PathVariable("id") Long id, @RequestBody Magasin magasin) {
        magasin.setId(id);
        return repository.save(magasin);
    }

    @RequestMapping(value="/{id}", method = RequestMethod.PATCH, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @Transactional
    public Magasin patch(@PathVariable("id") Long id, @RequestBody Magasin magasin) {
        Magasin databaseAddress  = repository.findOne(id);
        if (magasin.getName() != null) {
            databaseAddress.setName(magasin.getName());
        }
        if (magasin.getAddress() != null) {
            databaseAddress.setAddress(magasin.getAddress());
        }
        if (magasin.getVille() != null) {
            databaseAddress.setVille(magasin.getVille());
        }
        if (magasin.getCp() != null) {
            databaseAddress.setCp(magasin.getCp());
        }
        return repository.save(databaseAddress);
    }

    @RequestMapping(value="/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathParam("id") Long id) {
        repository.delete(id);
    }

    @RequestMapping(value="/produit/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Iterable<Produit> getAllProduit(@PathVariable("id") Long id) {
        Magasin magasin = repository.findOne(id);

        return magasin.getListProduit();
    }

    public MagasinRepository getRepository() {
        return repository;
    }

    public void setRepository(MagasinRepository repository) {
        this.repository = repository;
    }

}
