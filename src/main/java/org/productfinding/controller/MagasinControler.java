package org.productfinding.controller;

import org.productfinding.entity.Magasin;
import org.productfinding.entity.Produit;
import org.productfinding.entity.ProduitInMagasin;
import org.productfinding.repository.MagasinRepository;
import org.productfinding.repository.ProduitInMagasinRepository;
import org.productfinding.repository.ProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.websocket.server.PathParam;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/magasin")
public class MagasinControler {
    @Autowired
    private MagasinRepository repository;
    @Autowired
    private ProduitRepository produitRepository;
    @Autowired
    private ProduitInMagasinRepository produitInMagasinRepository;

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
        repository.save(magasin);

        List<ProduitInMagasin> listAsso = magasin.getListProduit();
        if (listAsso != null) {
            for (ProduitInMagasin produitInMag : listAsso) {
                produitInMag.getId().setMagasin(magasin);
                produitRepository.save(produitInMag.getId().getProduit());
                produitInMagasinRepository.save(produitInMag);
                produitInMag.getId().setMagasin(null);
            }
        }
        return magasin;
    }

    @RequestMapping(value="/{id}/logo", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Magasin update(@PathParam("id") Long id, @RequestParam("file") MultipartFile file) {
        Magasin magasin = repository.findOne(id);
        try {
            // Get the filename and build the local file path (be sure that the
            // application have write permissions on such directory)
            String filename = magasin.getId() + "_" + file.getOriginalFilename();
            String directory = "public/images";
            String filepath = Paths.get(directory, filename).toString();

            // Save the file locally
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(filepath)));
            stream.write(file.getBytes());
            stream.close();

            magasin.setLogoUrl(filepath);
            repository.save(magasin);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return magasin;
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

    @RequestMapping(value="/{id}/produits", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Iterable<Produit> getAllProduit(@PathVariable("id") Long id) {
        Magasin magasin = repository.findOne(id);
        List<Produit> listProduit = new ArrayList<Produit>();
        for (ProduitInMagasin prodInMagasin : magasin.getListProduit()) {
            Produit p = prodInMagasin.getId().getProduit();
            p.setPrix(prodInMagasin.getPrix());
            p.setLongitude(prodInMagasin.getLongitude());
            p.setLatitude(prodInMagasin.getLongitude());
            listProduit.add(p);
        }
        return listProduit;
    }

    public MagasinRepository getRepository() {
        return repository;
    }

    public void setRepository(MagasinRepository repository) {
        this.repository = repository;
    }

}
