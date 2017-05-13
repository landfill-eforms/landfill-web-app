package org.lacitysan.landfill.server.config;

import org.lacitysan.landfill.server.config.app.ApplicationConstant;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * The purpose of this configuration item is to separate the <code>PasswordEncoder</code> bean from the
 * <code>SecurityConfig</code> configuration item in order to avoid cycling bean dependencies.
 * @author Alvin Quach
 */
@Configuration
public class PasswordConfig {
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(ApplicationConstant.BCRYPT_STRENGTH);
	}

}
