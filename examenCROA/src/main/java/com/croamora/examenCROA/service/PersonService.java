/**
 * 
 */
package com.croamora.examenCROA.service;

import java.util.HashSet;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.croamora.examenCROA.dto.BorrowerDTO;
import com.croamora.examenCROA.dto.LenderDTO;
import com.croamora.examenCROA.entity.Authority;
import com.croamora.examenCROA.entity.Loan;
import com.croamora.examenCROA.entity.Person;
import com.croamora.examenCROA.entity.Wallet;
import com.croamora.examenCROA.repository.AuthorityRepository;
import com.croamora.examenCROA.repository.LoanRepository;
import com.croamora.examenCROA.repository.PersonRepository;
import com.croamora.examenCROA.repository.PersonTypeRepository;
import com.croamora.examenCROA.repository.UserRepository;
import com.croamora.examenCROA.repository.WalletRepository;
import com.croamora.examenCROA.utils.Utils;


/**
 * @author croamora
 *
 */
@Service
public class PersonService {

    @Autowired
    PersonRepository personRepository;
    
    @Autowired
    UserRepository userRepository;
    
    @Autowired
    PersonTypeRepository personTypeRepository;
    
    @Autowired
    AuthorityRepository authorityRepository;
    
    @Autowired
    LoanRepository loanRepository;
    
    @Autowired
    WalletRepository walletRepository;

    @Transactional
    public Person createNewLender(LenderDTO lenderDTO){
		//create new User and save to DB
    	com.croamora.examenCROA.entity.User appUser = new com.croamora.examenCROA.entity.User();
    	appUser.setUsername(lenderDTO.getEmail());
    	appUser.setEnabled(true);
    	appUser.setPassword(Utils.Passgenerator(lenderDTO.getPassword()));
    	Authority Authority = authorityRepository.findByAuthority("lender");
    	Set<Authority> authorities = new HashSet<Authority>();
    	authorities.add(Authority);
    	appUser.setAuthority(authorities);
    	userRepository.save(appUser);
    	
    	//create new Person, add persist User and save to DB
    	Person person = new Person();
    	person.setFirstName(lenderDTO.getFirstName());
    	person.setLastName(lenderDTO.getLastName());
    	person.setUser(appUser);
    	person.setPersonType(personTypeRepository.findByShortDescLabel("lender"));
    	personRepository.save(person);
    	
    	//create new Wallet register and save to DB
    	Wallet wallet = new Wallet();
    	wallet.setInflux(lenderDTO.getMoney());
    	wallet.setPerson(person);
    	walletRepository.save(wallet);
    	
    	return person;
    	
    }
    
    
    @Transactional
    public Person createNewBorrower(BorrowerDTO borrowerDTO){
		//create new User and save to DB
    	com.croamora.examenCROA.entity.User appUser = new com.croamora.examenCROA.entity.User();
    	appUser.setUsername(borrowerDTO.getEmail());
    	appUser.setEnabled(true);
    	appUser.setPassword(Utils.Passgenerator(borrowerDTO.getPassword()));
    	Authority Authority = authorityRepository.findByAuthority("borrower");
    	Set<Authority> authorities = new HashSet<Authority>();
    	authorities.add(Authority);
    	appUser.setAuthority(authorities);
    	userRepository.save(appUser);
    	
    	//create new Person, add persist User and save to DB
    	Person person = new Person();
    	person.setFirstName(borrowerDTO.getFirstName());
    	person.setLastName(borrowerDTO.getLastName());
    	person.setUser(appUser);
    	person.setPersonType(personTypeRepository.findByShortDescLabel("borrower"));
    	personRepository.save(person);
    	
    	//create new Loan and save to DB
    	Loan loan = new Loan();
    	loan.setPerson(person);
    	loan.setReason(borrowerDTO.getNeed());
    	loan.setDescription(borrowerDTO.getDescription());
    	loan.setAmount(borrowerDTO.getMoney());
    	loanRepository.save(loan);
    	
    	return person;
    	
    }


	public Person getPersonByUserName(String username) {
		com.croamora.examenCROA.entity.User appUser = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("No existe usuario"));
		return personRepository.findByUser(appUser);
	}
}