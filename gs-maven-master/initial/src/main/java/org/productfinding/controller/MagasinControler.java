package org.productfinding.controller;

import org.productfinding.entity.Magasin;
import org.productfinding.repository.MagasinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/magasin")
public class MagasinControler {
    @Autowired
    private MagasinRepository magasinRepository;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Iterable<Magasin> getAll() {
        return magasinRepository.findAll();
    }
}
