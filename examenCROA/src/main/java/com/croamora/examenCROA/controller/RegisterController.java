/**
 * 
 */
package com.croamora.examenCROA.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.croamora.examenCROA.dto.BorrowerDTO;
import com.croamora.examenCROA.dto.LenderDTO;
import com.croamora.examenCROA.service.PersonService;

/**
 * @author croamora
 *
 */
@Controller
@RequestMapping("register")
public class RegisterController {
	
	 @Autowired
	 PersonService personService;

	@RequestMapping(value ="/", method = RequestMethod.GET)
	public ModelAndView index() {
		LenderDTO lenderDTO = new LenderDTO();
		BorrowerDTO borrowerDTO  = new BorrowerDTO();
		ModelAndView mv = new ModelAndView("register");
		mv.addObject("lenderDTO", lenderDTO);
		mv.addObject("borrowerDTO", borrowerDTO);
		return mv;
	}
	
	
	@RequestMapping(value ="lender", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ModelAndView registerLender(@ModelAttribute LenderDTO lenderDTO) {
		if(lenderDTO != null) {
			personService.createNewLender(lenderDTO);
		}
		LenderDTO lenderDTOnew = new LenderDTO();
		BorrowerDTO borrowerDTO  = new BorrowerDTO();
		ModelAndView mv = new ModelAndView("register");
		mv.addObject("lenderDTO", lenderDTOnew);
		mv.addObject("borrowerDTO", borrowerDTO);
		mv.addObject("status", "Success");
		return mv;
	}

	@RequestMapping(value ="borrower", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ModelAndView registerborrower(@ModelAttribute BorrowerDTO borrowerDTO) {
		if(borrowerDTO != null) {
			personService.createNewBorrower(borrowerDTO);
		}
		LenderDTO lenderDTOnew = new LenderDTO();
		borrowerDTO  = new BorrowerDTO();
		ModelAndView mv = new ModelAndView("register");
		mv.addObject("lenderDTO", lenderDTOnew);
		mv.addObject("borrowerDTO", borrowerDTO);
		mv.addObject("success", "true");
		return mv;
	}
}