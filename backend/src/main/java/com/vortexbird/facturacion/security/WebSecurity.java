package com.vortexbird.facturacion.security;

import static com.vortexbird.facturacion.security.Constants.ACTUATOR_URL;
import static com.vortexbird.facturacion.security.Constants.API_DOCS;
import static com.vortexbird.facturacion.security.Constants.LOGIN_URL;
import static com.vortexbird.facturacion.security.Constants.SWAGGER_UI;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

/**
 * @author Zathura Code Generator Version 22.08 http://zathuracode.org/
 *         www.zathuracode.org
 * @generationDate 2022-08-23T11:10:20.573413
 * 
 */

@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		/*
		 * 1. Se desactiva el uso de cookies 2. Se activa la configuraciï¿½n CORS con
		 * los valores por defecto 3. Se desactiva el filtro CSRF 4. Se indica que el
		 * login no requiere autenticaciï¿½n 5. Se indica que el resto de URLs esten
		 * securizadas
		 */
		httpSecurity.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().cors().and()
				.csrf().disable().authorizeRequests().antMatchers(LOGIN_URL, ACTUATOR_URL, API_DOCS, SWAGGER_UI)
				.permitAll().anyRequest().authenticated().and()
				.addFilter(new JWTAuthorizationFilter(authenticationManager()));
	}

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		// Se define la clase que recupera los usuarios y el algoritmo para procesar los
		// passwords
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
	}

	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedMethods(Arrays.asList("GET", "PUT", "POST", "DELETE", "OPTIONS", "HEAD"));
		configuration.addAllowedHeader("*");
		configuration.addAllowedOrigin("*");
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}

}
