package com.croamora.examenCROA.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.croamora.examenCROA.entity.Loan;
import com.croamora.examenCROA.entity.Person;

/**
 * @author croamora
 *
 */
@Repository
public interface LoanRepository extends CrudRepository<Loan, Long>  {
	
	 public Loan findByPerson(Person person);
	 
     @Query(value = "SELECT l.id as idLoan, CONCAT(p.first_name,' ',p.last_name) as name,l.reason ,l.description,l.amount as needed, p.id as personId FROM loan l LEFT JOIN person p ON (p.id = l.id_person);", 
    		  nativeQuery = true)
     List<Object> getLoanList();
     
     
     @Query(value = "SELECT CONCAT(p.first_name,' ' ,p.last_name) as name ,l.reason ,l.description,l.amount as needed ,p.id as personId, SUM(w.output) as lent FROM wallet w LEFT JOIN loan l ON (l.id = w.id_loan) LEFT JOIN person p ON (p.id = l.id_person) WHERE w.id_person = ?1 AND w.influx IS NULL GROUP BY name;", 
     		  nativeQuery = true)
     List<Object> getLoanLentList(Integer personId);
	 

}
