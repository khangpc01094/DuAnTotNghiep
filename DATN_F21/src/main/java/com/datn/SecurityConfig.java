package com.datn;

import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;

import com.datn.entity.Users;
import com.datn.service.AuthorizationService;
import com.datn.service.UserService;





@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	UserService svUserService;
	
	@Autowired
	AuthorizationService svAuthorizationService;
	
	@Autowired
	BCryptPasswordEncoder pe;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(username->{
			try {
				Users user = svUserService.findById(username);
				String password = pe.encode(user.getPassword());				
				Integer[] roles = svAuthorizationService.findRoleByUsername(username).stream()
						.map(role ->role.getId())
						.collect(Collectors.toList()).toArray(new Integer[0]);
				
				return User.withUsername(username).password(password).roles(roles.toString()).build();
			} catch (NoSuchElementException e) {
				throw new UsernameNotFoundException(username+ " not fount!");
			}
		});
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
//		http.authorizeRequests()
//		   .antMatchers("/order/**").authenticated()
//		   .antMatchers("/admin/**","/assets/admin/**","/rest/categories/**").hasAnyRole("STAF","DIRE")
//		   .anyRequest().permitAll();
//		
		http.formLogin()
	   .loginPage("/security/login/form")
	   .loginProcessingUrl("/security/login")
	   .defaultSuccessUrl("/index",true)
	   .failureUrl("/security/login/error");
		
		http.rememberMe()
		   .tokenValiditySeconds(86400);
		
		http.exceptionHandling()
		    .accessDeniedPage("/security/unauthoried");
		
		http.logout()
		   .logoutUrl("/security/logoff")
		   .logoutSuccessUrl("/security/logoff/success");
		
		http.oauth2Login()
		  .loginPage("/security/login/form")
		  .defaultSuccessUrl("/index",true)
		  .failureUrl("/auth/login/error")
		  .authorizationEndpoint()
		  .baseUri("/oauth2/authorization");
	}
	
	@Bean
	public BCryptPasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
	public void loginFromOAuth2(OAuth2AuthenticationToken oauth2) {
		String fullname = oauth2.getPrincipal().getAttribute("name");
		String email = oauth2.getPrincipal().getAttribute("email");
		String password = Long.toHexString(System.currentTimeMillis());
		
		UserDetails user = User.withUsername(email)
				.password(pe.encode(password)).roles("1").build();
		Authentication auth=new UsernamePasswordAuthenticationToken(user, null,user.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(auth);
	}
	
	
}
