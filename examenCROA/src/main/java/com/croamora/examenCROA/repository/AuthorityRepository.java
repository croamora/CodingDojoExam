package com.croamora.examenCROA.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.croamora.examenCROA.entity.Authority;

/**
 * @author croamora
 *
 */
@Repository
public interface AuthorityRepository extends CrudRepository<Authority, Long>  {
	
	 public Authority findByAuthority(String authority);

}
