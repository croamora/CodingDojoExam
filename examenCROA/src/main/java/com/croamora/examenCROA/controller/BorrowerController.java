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
import org.springframework.web.servlet.ModelAndView;

import com.croamora.examenCROA.dto.BorrowerLentDTO;
import com.croamora.examenCROA.entity.Person;
import com.croamora.examenCROA.service.PersonService;
import com.croamora.examenCROA.service.WalletService;

/**
 * @author croamora
 *
 */
@Controller
@RequestMapping("borrower")
public class BorrowerController {
	
	 @Autowired
	 PersonService personService;
	 
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
		List<BorrowerLentDTO> list = walletService.getBorrowerLentList(person.getId().intValue());
		Integer amountNeeded = walletService.getAmountNeeded(person);
		Integer amountRaised= walletService.getAmountRaised(person.getId().intValue());
		ModelAndView mv = new ModelAndView("borrower");
		mv.addObject("userName", person.getFirstName()+" "+person.getLastName());
		mv.addObject("dataTable", list);
		mv.addObject("amountNeeded", NumberFormat.getCurrencyInstance(new Locale("en", "US")).format(amountNeeded));
		mv.addObject("amountRaised", NumberFormat.getCurrencyInstance(new Locale("en", "US")).format(amountRaised != null ? amountRaised : 0));
		
		return mv;
	}
	
	
}