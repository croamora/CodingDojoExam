/**
 * 
 */
package com.croamora.examenCROA.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.croamora.examenCROA.entity.Authority;
import com.croamora.examenCROA.repository.UserRepository;


/**
 * @author croamora
 *
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;
	
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
	    //Search for the user with the repository and if it does not exist throw an exception
	    com.croamora.examenCROA.entity.User appUser = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("No existe usuario"));
			
	    //Map our Authority list with that of spring security
	    List grantList = new ArrayList();
	    for (Authority authority: appUser.getAuthority()) {
	        // ROLE_1, ROLE_2,..
	        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(authority.getAuthority());
	            grantList.add(grantedAuthority);
	    }
			
	    //Create the UserDetails object to be logged and return it.
	    UserDetails user = (UserDetails) new User(appUser.getUsername(), appUser.getPassword(), grantList);
	         return user;
    }
}