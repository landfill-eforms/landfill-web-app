package org.lacitysan.landfill.server.config;

import java.util.ArrayList;
import java.util.List;

import org.lacitysan.landfill.server.config.app.ApplicationConstant;
import org.lacitysan.landfill.server.security.filters.TokenAuthenticationFilter;
import org.lacitysan.landfill.server.security.filters.TokenLoginFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author Alvin Quach
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	AuthenticationManager authenticationManager;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.csrf().disable()
		//.anonymous().disable()
		.authorizeRequests()
			.antMatchers("/").permitAll()
			.antMatchers(HttpMethod.POST, ApplicationConstant.LOGIN_PATH).permitAll()
			.antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
			.anyRequest().authenticated()
			.and()
		.addFilterBefore(new TokenLoginFilter(ApplicationConstant.LOGIN_PATH, authenticationManager), UsernamePasswordAuthenticationFilter.class)
		.addFilterBefore(new TokenAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurerAdapter() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**");
			}
//			@Override
//			public void configureMessageConverters(List<HttpMessageConverter<?>> converters){
//			    List<MediaType> supportedMediaTypes = new ArrayList<>();
//			    supportedMediaTypes.add(MediaType.APPLICATION_JSON);
//			    supportedMediaTypes.add(MediaType.TEXT_PLAIN);
//			    MappingJackson2HttpMessageConverter converter=new MappingJackson2HttpMessageConverter();
//			    converter.setObjectMapper(new HibernateAwareObjectMapper());
//			    converter.setPrettyPrint(true);
//			    converter.setSupportedMediaTypes(supportedMediaTypes);
//			    converters.add(converter);
//			    super.configureMessageConverters(converters);
//			}
		};
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
