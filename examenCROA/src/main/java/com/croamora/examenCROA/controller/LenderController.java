/**
 * 
 */
package com.croamora.examenCROA.controller;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.croamora.examenCROA.dto.LoantoHelpDTO;
import com.croamora.examenCROA.dto.PeopleToLentDTO;
import com.croamora.examenCROA.entity.Person;
import com.croamora.examenCROA.entity.Wallet;
import com.croamora.examenCROA.service.LoanService;
import com.croamora.examenCROA.service.PersonService;
import com.croamora.examenCROA.service.WalletService;

/**
 * @author croamora
 *
 */
@Controller
@RequestMapping("lender")
public class LenderController {
	
	 @Autowired
	 PersonService personService;
	 
	 @Autowired
	 LoanService loanService;
	 
	 @Autowired
	 WalletService walletService;

	@RequestMapping(value ="/", method = RequestMethod.GET)
	public ModelAndView index() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String userName = "";
		if(principal instanceof User) {
			userName = ((User)principal).getUsername();
		}
		Person person = personService.getPersonByUserName(userName);
		List<LoantoHelpDTO> dataTableHelp = loanService.getLoanList();
		List<PeopleToLentDTO> dataTableLent = loanService.getLoanLentList(person.getId().intValue());
		ModelAndView mv = new ModelAndView("lender");
		Integer balance = walletService.getBalance(person.getId().intValue());
		mv.addObject("userName", person.getFirstName()+" "+person.getLastName());
		mv.addObject("dataTableHelp",dataTableHelp);
		mv.addObject("dataTableLent",dataTableLent);
		mv.addObject("balance", NumberFormat.getCurrencyInstance(new Locale("en", "US")).format(balance));
		
	
		return mv;
	}
	
	
	@RequestMapping(value ="send", method = RequestMethod.POST)
	public ModelAndView lend(@RequestParam(value = "idLoan", required = true) String idLoan, @RequestParam(value = "money", required = true) Integer money) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String userName = "";
		if(principal instanceof User) {
			userName = ((User)principal).getUsername();
		}
		ModelAndView mv = new ModelAndView("lender");
		Person person = personService.getPersonByUserName(userName);
		Integer balance = walletService.getBalance(person.getId().intValue());
		if(balance >= money) {
			Wallet wallet = new Wallet();
			wallet.setPerson(person);
			wallet.setLoan(loanService.getLoanById(idLoan));
			wallet.setOutput(money);
			walletService.save(wallet);
			mv.addObject("success",true);
		}else {
			mv.addObject("errorCredit",true);
		}
		
		balance = walletService.getBalance(person.getId().intValue());
		List<LoantoHelpDTO> dataTableHelp = loanService.getLoanList();
		List<PeopleToLentDTO> dataTableLent = loanService.getLoanLentList(person.getId().intValue());
		
		mv.addObject("userName", person.getFirstName()+" "+person.getLastName());
		mv.addObject("dataTableHelp",dataTableHelp);
		mv.addObject("dataTableLent",dataTableLent);
		mv.addObject("balance", NumberFormat.getCurrencyInstance(new Locale("en", "US")).format(balance));
		
	
		return mv;
	}
	
}