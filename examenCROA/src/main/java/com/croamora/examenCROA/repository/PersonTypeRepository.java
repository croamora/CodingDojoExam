package com.croamora.examenCROA.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.croamora.examenCROA.entity.PersonType;

/**
 * @author croamora
 *
 */
@Repository
public interface PersonTypeRepository extends CrudRepository<PersonType, Long>  {

	public PersonType findByShortDescLabel(String shortDescLabel);
}
