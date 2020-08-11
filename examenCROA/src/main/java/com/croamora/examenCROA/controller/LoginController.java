/**
 * 
 */
package com.croamora.examenCROA.controller;

import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

/**
 * @author croamora
 *
 */
@Controller
@RequestMapping("/")
public class LoginController {

	@RequestMapping(value ={"/","/login"}, method = RequestMethod.GET)
	public String index() {
		return "login";
	}
	
	@RequestMapping(value ="/menu", method = RequestMethod.GET)
	public ModelAndView home() {
		RedirectView rv;
		if (currentUserHasRole("borrower")) {
			rv = new RedirectView("borrower/");
			rv.setExposeModelAttributes(false);
			return new ModelAndView(rv);
		} else if (currentUserHasRole("lender")){
			rv = new RedirectView("lender/");
			rv.setExposeModelAttributes(false);
			return new ModelAndView(rv);
		}else {
			rv = new RedirectView("logout");
			return new ModelAndView(rv);
		}
	}
	
	@RequestMapping(value ="/borrower", method = RequestMethod.GET)
	public String borrower() {
		return "borrower";
	}
	
	@RequestMapping(value ="/lender", method = RequestMethod.GET)
	public String lender() {
		return "lender";
	}
	
	@RequestMapping(value ="/register", method = RequestMethod.GET)
	public ModelAndView register() {
		RedirectView rv = new RedirectView("register/");
		rv.setExposeModelAttributes(false);
		return new ModelAndView(rv);
	}
	

	/** Returns whether the current user has at least one of the given roles */
	public static boolean currentUserHasRole(String... roles) {
	  if (roles == null || roles.length == 0) return false;

	  Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	  if (authentication != null) {
	    Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
	    if (authorities == null) throw new IllegalStateException("No user currently logged in");

	    for (String role : roles) {
	      for (GrantedAuthority grantedAuthority : authorities) {
	        if (role.equals(grantedAuthority.getAuthority())) return true;
	      }
	    }
	  }
	  return false;
	}

}