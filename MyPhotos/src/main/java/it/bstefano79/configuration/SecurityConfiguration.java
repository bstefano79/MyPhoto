package it.bstefano79.configuration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
	
	Logger logger = LogManager.getLogger(SecurityConfiguration.class);
	
	@Autowired
	UserDetailsService userDetailsService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests().
			antMatchers("/admin").hasRole("ADMIN").
			antMatchers("/admin/**").hasRole("ADMIN").
			antMatchers("/").permitAll().
			and().formLogin().
			loginPage("/login").and().csrf().disable().
			logout().logoutSuccessUrl("/login");
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		logger.debug("ENTRO IN CONFIGURE");
		auth.userDetailsService(userDetailsService);
	}

	

}
