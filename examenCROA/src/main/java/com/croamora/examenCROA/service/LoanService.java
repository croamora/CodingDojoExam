/**
 * 
 */
package com.croamora.examenCROA.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.croamora.examenCROA.dto.LoantoHelpDTO;
import com.croamora.examenCROA.dto.PeopleToLentDTO;
import com.croamora.examenCROA.entity.Loan;
import com.croamora.examenCROA.entity.Person;
import com.croamora.examenCROA.repository.LoanRepository;
import com.croamora.examenCROA.repository.WalletRepository;


/**
 * @author croamora
 *
 */
@Service
public class LoanService {


    @Autowired
    LoanRepository loanRepository;
    
    @Autowired
    WalletRepository walletRepository;
    
    public List<LoantoHelpDTO> getLoanList(){
    	List<Object> list =  loanRepository.getLoanList();
    	return convertToDTO(list);
    }

    public List<PeopleToLentDTO> getLoanLentList(Integer personId){
    	List<Object> list =  loanRepository.getLoanLentList(personId);
    	return convertToLentDTO(list);
    }

	
    private List<PeopleToLentDTO> convertToLentDTO(List<Object> list) {
		List<PeopleToLentDTO> dtoList = new ArrayList<PeopleToLentDTO>();
		for (Object object : list) {
			Object[] data = (Object[]) object;
			PeopleToLentDTO peopleToLentDTO = new PeopleToLentDTO();
			if(data[0] != null) {
				peopleToLentDTO.setName(data[0].toString());
				peopleToLentDTO.setReason(data[1].toString());
				peopleToLentDTO.setDescription(data[2].toString());
				peopleToLentDTO.setAmountNeeded(Integer.parseInt(data[3].toString()));
				peopleToLentDTO.setAmountReised(walletRepository.raised(Integer.parseInt(data[4].toString())));
				peopleToLentDTO.setAmountLent(Integer.parseInt(data[5].toString()));
				dtoList.add(peopleToLentDTO);
			}
		}
		return dtoList;
	}

	private List<LoantoHelpDTO> convertToDTO(List<Object> list) {
		List<LoantoHelpDTO> dtoList = new ArrayList<LoantoHelpDTO>();
		for (Object object : list) {
			Object[] data = (Object[]) object;
			LoantoHelpDTO loantoHelpDTO = new LoantoHelpDTO();
			if(data[0] != null) {
				loantoHelpDTO.setIdLead(Integer.parseInt(data[0].toString()));
				loantoHelpDTO.setName(data[1].toString());
				loantoHelpDTO.setReason(data[2].toString());
				loantoHelpDTO.setDescription(data[3].toString());
				loantoHelpDTO.setAmountNeeded(Integer.parseInt(data[4].toString()));
				loantoHelpDTO.setAmountReised(walletRepository.raised(Integer.parseInt(data[5].toString())));
				dtoList.add(loantoHelpDTO);
			}
		}
		return dtoList;
	}

	public Loan getLoanByPerson(Person person) {
		return loanRepository.findByPerson(person);
	}

	public Loan getLoanById(String idLoan) {
		long id = Long.parseLong(idLoan);
		return loanRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("ERROR"));
	}




    
}