package com.spa.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
	
	private final DatabaseLoginSuccessHandler databaseLoginHandler;
	
	public WebSecurityConfig(DatabaseLoginSuccessHandler databaseLoginHandler) {
		this.databaseLoginHandler = databaseLoginHandler;
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(authorizeRequests -> authorizeRequests
			.requestMatchers("/admin/**").hasAuthority("ADMIN")
			.requestMatchers("/customer/**").authenticated()
			.anyRequest().permitAll()
			)		
			.formLogin(form -> form
				.loginPage("/login")
				.successHandler(databaseLoginHandler)
				.permitAll())
			.logout(logout -> logout
					.logoutSuccessUrl("/")
					.permitAll())
			.exceptionHandling(exeption -> exeption.accessDeniedPage("/403"));
		
		return http.build();
	}
    
    @Bean
    WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers("/resources/**", "/static/**", "/css/**", "/templates/**", "/js/**");
    }
    
	@Bean
	UserDetailsService userDetailsService() {
		return new CustomerUserDetailsService();
	}
    
	@Bean
	DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

		authProvider.setUserDetailsService(userDetailsService());
		authProvider.setPasswordEncoder(passwordEncoder());

		return authProvider;
	}
}
