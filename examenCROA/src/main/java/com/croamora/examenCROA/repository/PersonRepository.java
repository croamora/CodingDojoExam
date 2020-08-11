package com.croamora.examenCROA.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.croamora.examenCROA.entity.Person;
import com.croamora.examenCROA.entity.User;

/**
 * @author croamora
 *
 */
@Repository
public interface PersonRepository extends CrudRepository<Person, Long>  {
	
	 public Person findByUser(User user);
}
