package org.productfinding.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.productfinding.repository.UserRepository;
import org.productfinding.entity.User;

import java.util.stream.Collectors;

/**
 * Created by alehmann on 15/01/2016.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private UserRepository userRepositoy;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Iterable<User> getAll() {
        return userRepositoy.findAll();
    }

    @RequestMapping(value="/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public User get(@PathVariable("id") Long id) {
        return userRepositoy.findOne(id);
    }

    @RequestMapping(value="/{id}", method = RequestMethod.PATCH, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @Transactional
    public User patch(@PathVariable("id") Long id, @RequestBody User user) {
        User databaseUser  = userRepositoy.findOne(id);
        if (user.getUsername() != null) {
            databaseUser.setUsername(user.getUsername());
        }
        if (user.getPassword() != null) {
            databaseUser.setPassword(user.getPassword());
        }
        if (user.getFirstName() != null) {
            databaseUser.setFirstName(user.getFirstName());
        }
        if (user.getLastName() != null) {
            databaseUser.setLastName(user.getLastName());
        }
        return userRepositoy.save(databaseUser);
    }

    @RequestMapping(value="/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") Long id) {
        userRepositoy.delete(id);
    }

    public UserRepository getUserRepositoy() {
        return userRepositoy;
    }

    public void setUserRepositoy(UserRepository userRepositoy) {
        this.userRepositoy = userRepositoy;
    }
}
