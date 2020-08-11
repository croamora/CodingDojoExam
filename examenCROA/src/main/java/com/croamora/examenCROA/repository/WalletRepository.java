package com.croamora.examenCROA.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.croamora.examenCROA.entity.Wallet;

/**
 * @author croamora
 *
 */
@Repository
public interface WalletRepository extends CrudRepository<Wallet, Long>  {
	
    @Query(value = "SELECT CONCAT(p.first_name,' ',p.last_name) as name,u.username as email, wallet.output as amount FROM wallet wallet LEFT JOIN loan lo on (lo.id = wallet.id_loan) LEFT JOIN person p ON (p.id = wallet.id_person) LEFT JOIN user u ON (p.id_user = u.id) WHERE wallet.id_loan = (SELECT id FROM loan where id_person = ?1);", 
  		  nativeQuery = true)
    List<Object> getBorrowerLentList(Integer personId);
    
    @Query(value = "SELECT SUM(wallet.output) as raised FROM wallet wallet where wallet.id_loan = (SELECT id FROM loan where id_person = ?1);", 
    		  nativeQuery = true)
    Integer raised(Integer personId);
    
    @Query(value = "SELECT SUM(output) as balance FROM wallet where id_person=?1", nativeQuery = true)
    Integer outData(Integer personId);
    
    @Query(value = "SELECT SUM(influx) as balance FROM wallet where id_person=?1", nativeQuery = true)
    Integer inData(Integer personId);
    
}