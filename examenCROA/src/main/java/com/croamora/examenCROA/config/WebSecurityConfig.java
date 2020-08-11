package com.croamora.examenCROA.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.croamora.examenCROA.service.UserDetailsServiceImpl;

/**
 * @author croamora
 *
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

	//Required to prevent security from being applied to resources
    String[] resources = new String[]{
            "/include/**","/css/**","/fonts/**","/icons/**","/img/**","/js/**","/layer/**"
    };
    //password encryptor
    BCryptPasswordEncoder bCryptPasswordEncoder;
    //User Detail Service
    @Autowired
    UserDetailsServiceImpl userDetailsService;
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
	        .antMatchers(resources).permitAll()  
	        .antMatchers("/","/index","/register*","/register/*").permitAll()
	        .antMatchers("/borrower*").access("hasRole('borrower')")
	        .antMatchers("/lender*").access("hasRole('lender')")
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .loginPage("/login")
                .permitAll()
                .defaultSuccessUrl("/menu")
                .failureUrl("/login?error=true")
                .usernameParameter("username")
                .passwordParameter("password")
                .and()
            .logout()
                .permitAll()
                .logoutSuccessUrl("/login?logout");
    }
    
    
    
    //get password encryptor	
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
		bCryptPasswordEncoder = new BCryptPasswordEncoder(4);
        return bCryptPasswordEncoder;
    }
	
	
   //Registra el service para usuarios y el encriptador de contrasena
   @Autowired
   public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception { 
        // Setting Service to find User in the database.
        // And Setting PassswordEncoder
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());     
   }
}
