package com.landfill_eforms.server.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.landfill_eforms.server.security.TokenAuthenticationFilter;
import com.landfill_eforms.server.security.TokenLoginFilter;

/**
 * @author Alvin Quach
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	public static final String LOGIN_PATH = "/login";
	
	@Autowired
	UserDetailsService userDetailsService;
	
    @Autowired
    public void globalUserDetails(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//        .withUser("aquach").password("  ").roles("ADMIN").and()
//        .withUser("asdf").password("asdf").roles("USER");
    	auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

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
            // We filter the api/login requests
            .addFilterBefore(new TokenLoginFilter(LOGIN_PATH, authenticationManager()), UsernamePasswordAuthenticationFilter.class)
            // And filter other requests to check the presence of JWT in header
            .addFilterBefore(new TokenAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }
    
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
    
}
