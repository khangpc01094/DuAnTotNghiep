package com.datn;

import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import com.datn.entity.Users;
import com.datn.service.AuthorizationService;
import com.datn.service.UserService;


import org.springframework.beans.factory.annotation.Autowired;
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
        auth.userDetailsService(username -> {
        	
            try {
                Users user = svUserService.findByUsername(username);
                String password = pe.encode(user.getPassword());
                String[] roles = svAuthorizationService.findRoleByUserId(user.getUserid()).stream()
                        .map(role -> role.getId())
                        .collect(Collectors.toList()).toArray(new String[0]);
                return User.withUsername(user.getUserid()).password(password).roles(roles).build();
            } catch (NoSuchElementException e) {
                throw new UsernameNotFoundException(username + " not fount!");
            }
        });
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
//        http.authorizeRequests()
//        .antMatchers("/rest/user/findall").authenticated()
//         .antMatchers("/rest/user/findall").hasAnyRole("ADMI","BUYE");
        // .anyRequest().permitAll();
        //
        http.formLogin()
                .loginPage("/security/login/form")
                .loginProcessingUrl("/security/login")
                .defaultSuccessUrl("/index", false)
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
                .defaultSuccessUrl("/security_oauth2/login/success", true)
                .failureUrl("/auth/login/error")
                .authorizationEndpoint()
                .baseUri("/oauth2/authorization");
    }

    

    public void loginFromOAuth2(OAuth2AuthenticationToken oauth2) {
    	String username = oauth2.getName();
    	String password = Long.toHexString(System.currentTimeMillis()); 
        String fullname = oauth2.getPrincipal().getAttribute("name");
        String email = oauth2.getPrincipal().getAttribute("email");
          
        Users user = svUserService.saveUserAuth2(username, password, fullname,email);
        String passwordShow = pe.encode(user.getPassword());
        String[] roles = svAuthorizationService.findRoleByUserId(user.getUserid()).stream()
                .map(role -> role.getId())
                .collect(Collectors.toList()).toArray(new String[0]);
        
        							
        UserDetails userDetails = User.withUsername(user.getUserid()).password(passwordShow).roles(roles).build();
                
        Authentication auth = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(auth);
    }
    
 
}
