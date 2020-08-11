/**
 * 
 */
package com.croamora.examenCROA.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.croamora.examenCROA.dto.BorrowerLentDTO;
import com.croamora.examenCROA.entity.Loan;
import com.croamora.examenCROA.entity.Person;
import com.croamora.examenCROA.entity.Wallet;
import com.croamora.examenCROA.repository.LoanRepository;
import com.croamora.examenCROA.repository.WalletRepository;


/**
 * @author croamora
 *
 */
@Service
public class WalletService {

    
    @Autowired
    LoanRepository loanRepository;
    
    @Autowired
    WalletRepository walletRepository;
    
  //get lent list By person id
    public List<BorrowerLentDTO> getBorrowerLentList(Integer personId){
    	List<Object> list =  walletRepository.getBorrowerLentList(personId);
    	return convertToDTO(list);
    }

    //get getAmountNeeded By person
	public Integer getAmountNeeded(Person person) {
		Loan loan = loanRepository.findByPerson(person);
		return loan.getAmount();
	}
	
	//get AmountRaised By person id
	public Integer getAmountRaised(Integer personId) {
		return walletRepository.raised(personId);
	}

	//get balance By person id
	public Integer getBalance(Integer personId) {
		Integer in = walletRepository.inData(personId);
		Integer out = walletRepository.outData(personId);
		return (in != null ? in : 0) - (out != null ? out : 0);
	}
	
	//convert object to DTO 
	private List<BorrowerLentDTO> convertToDTO(List<Object> list) {
		List<BorrowerLentDTO> dtoList = new ArrayList<BorrowerLentDTO>();
		for (Object object : list) {
			Object[] data = (Object[]) object;
			BorrowerLentDTO borrowerLentDTO = new BorrowerLentDTO();
			borrowerLentDTO.setName(data[0].toString());
			borrowerLentDTO.setEmail(data[1].toString());
			borrowerLentDTO.setAmount(Integer.parseInt(data[2].toString()));
			dtoList.add(borrowerLentDTO);
		}
		return dtoList;
	}

	//save the wallet
	public void save(Wallet wallet) {
		walletRepository.save(wallet);
	}




    
}