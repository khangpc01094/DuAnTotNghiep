package com.datn;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import com.datn.entity.Users;
import com.datn.service.AuthorizationService;
import com.datn.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;

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

//	@Autowired
//	private UserDetailsService userDetailsService;

//	@Autowired
//	DaoAuthenticationProvider authenticationProvider;
	
//	@Override
//  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//      String email = authentication.getName();
//      String password = authentication.getCredentials().toString();
//
//      Users user = svUserService.timUserByEmail(email);
//      if (user != null) {
//          BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//          String comparePassword = user.getPassword();
//          if (passwordEncoder.matches(password, comparePassword)){
//              return new UsernamePasswordAuthenticationToken(email, password,
//                      Arrays.asList(new SimpleGrantedAuthority(user.getRoleName())));
//          }
//      }
//      return null;
//  }
//
	@Bean
	public DaoAuthenticationProvider authProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService());
		authProvider.setPasswordEncoder(getPasswordEncoder());
		return authProvider;
	}
	

	@Autowired
	private UserDetailsService jwtUserDetailsService;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(jwtUserDetailsService).passwordEncoder(getPasswordEncoder());
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(username -> {
			System.err.println("username >> " + username);
			try {
				Users user = svUserService.findByUsername(username);
				String password = pe.encode(user.getPassword());
				System.err.println("password >> " + password);
				Integer[] roles = svAuthorizationService.findRoleByUserId(user.getUserid()).stream()
						.map(role -> role.getId()).collect(Collectors.toList()).toArray(new Integer[0]);

//				 auth.authenticationProvider(authProvider());

				return User.withUsername(user.getUserid()).password(password).roles(roles.toString()).build();
			} catch (NoSuchElementException e) {
				throw new UsernameNotFoundException(username + " not found!");
			}
		});

	}

//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.userDetailsService(username -> {
//			System.err.println("username >> " + username);
//			try {
//				Users user = svUserService.findByUsername(username);
//				String password = pe.encode(user.getPassword());
//				System.err.println("password >> " + password);
//				Integer[] roles = svAuthorizationService.findRoleByUserId(user.getUserid()).stream()
//						.map(role -> role.getId()).collect(Collectors.toList()).toArray(new Integer[0]);
//				
//				return User.withUsername(user.getUserid()).password(password).roles(roles.toString()).build();
//			} catch (NoSuchElementException e) {
//				throw new UsernameNotFoundException(username + " not found!");
//			}
//		});
//		
////		 auth.authenticationProvider(authProvider());
//	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		// http.authorizeRequests()
		// .antMatchers("/order/**").authenticated()
		// .antMatchers("/admin/**","/assets/admin/**","/rest/categories/**").hasAnyRole("STAF","DIRE")
		// .anyRequest().permitAll();
		//
		http.formLogin().loginPage("/security/login/form").loginProcessingUrl("/security/login")
				.defaultSuccessUrl("/index", true).failureUrl("/security/login/error");

		http.rememberMe().tokenValiditySeconds(86400);

		http.exceptionHandling().accessDeniedPage("/security/unauthoried");

		http.logout().logoutUrl("/security/logoff").logoutSuccessUrl("/security/logoff/success");

		http.oauth2Login().loginPage("/security/login/form").defaultSuccessUrl("/security_oauth2/login/success", true)
				.failureUrl("/auth/login/error").authorizationEndpoint().baseUri("/oauth2/authorization");
	}

	@Bean
	public BCryptPasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	public void loginFromOAuth2(OAuth2AuthenticationToken oauth2) {
		String username = oauth2.getName();
		String password = Long.toHexString(System.currentTimeMillis());
		String fullname = oauth2.getPrincipal().getAttribute("name");
		String email = oauth2.getPrincipal().getAttribute("email");

		Users user = svUserService.saveUserAuth2(username, password, fullname, email);
		String passwordShow = pe.encode(user.getPassword());
		Integer[] roles = svAuthorizationService.findRoleByUserId(user.getUserid()).stream().map(role -> role.getId())
				.collect(Collectors.toList()).toArray(new Integer[0]);

		UserDetails userDetails = User.withUsername(user.getUserid()).password(passwordShow).roles(roles.toString())
				.build();

		Authentication auth = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(auth);
	}



}
