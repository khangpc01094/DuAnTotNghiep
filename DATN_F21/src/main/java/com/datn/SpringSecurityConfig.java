//package com.datn;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
//import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
//@Configuration
//@EnableGlobalMethodSecurity(prePostEnabled = true)
//public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Autowired
//    private SecurityConfig authProvider;
//
//    @Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http.csrf().disable();
//		// http.authorizeRequests()
//		// .antMatchers("/order/**").authenticated()
//		// .antMatchers("/admin/**","/assets/admin/**","/rest/categories/**").hasAnyRole("STAF","DIRE")
//		// .anyRequest().permitAll();
//		//
//		http.formLogin().loginPage("/security/login/form").loginProcessingUrl("/security/login")
//				.defaultSuccessUrl("/index", true).failureUrl("/security/login/error");
//
//		http.rememberMe().tokenValiditySeconds(86400);
//
//		http.exceptionHandling().accessDeniedPage("/security/unauthoried");
//
//		http.logout().logoutUrl("/security/logoff").logoutSuccessUrl("/security/logoff/success");
//
//		http.oauth2Login().loginPage("/security/login/form").defaultSuccessUrl("/security_oauth2/login/success", true)
//				.failureUrl("/auth/login/error").authorizationEndpoint().baseUri("/oauth2/authorization");
//	}
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) {
//        auth.authenticationProvider((AuthenticationProvider) authProvider);
//    }
//
////    @Bean
////    public RoleHierarchy roleHierarchy() {
////        RoleHierarchyImpl r = new RoleHierarchyImpl();
////        r.setHierarchy("ROLE_ADMIN >  ROLE_USER");
////        return r;
////    }
//
//}
