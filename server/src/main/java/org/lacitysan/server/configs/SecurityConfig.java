package com.landfill_eforms.server.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.landfill_eforms.server.security.filters.TokenAuthenticationFilter;
import com.landfill_eforms.server.security.filters.TokenLoginFilter;

/**
 * @author Alvin Quach
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	public static final String LOGIN_PATH = "/login";

	@Autowired
	AuthenticationManager authenticationManager;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.csrf().disable()
		//.anonymous().disable()
		.authorizeRequests()
			.antMatchers("/").permitAll()
			.antMatchers(HttpMethod.POST, LOGIN_PATH).permitAll()
			.anyRequest().authenticated()
			.and()
		.addFilterBefore(new TokenLoginFilter(LOGIN_PATH, authenticationManager), UsernamePasswordAuthenticationFilter.class)
		.addFilterBefore(new TokenAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
