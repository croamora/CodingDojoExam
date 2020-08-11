/**
 * 
 */
package com.croamora.examenCROA.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author croamora
 *
 */
public class Utils {

	//return encripted String
	public static String Passgenerator (String pass){
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(4);
		return bCryptPasswordEncoder.encode(pass);
	}

}
