package org.productfinding.repository;

import org.springframework.data.repository.CrudRepository;
import org.productfinding.entity.User;

/**
 * Created by alehmann on 15/01/2016.
 */
public interface UserRepository extends CrudRepository<User, Long> {
}
