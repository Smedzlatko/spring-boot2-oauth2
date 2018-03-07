package com.smedzl.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	private static final String LOGIN = "/login";
	private static final String LOGOUT_SUCCESS = "/login?logout";
	private static final String PROFILE = "/profile";

	private final UserDetailsService userDetailsService;
	private final AuthenticationManager authenticationManager;

	public WebSecurityConfig(UserDetailsService userDetailsService, @Lazy AuthenticationManager authenticationManager) {
		this.userDetailsService = userDetailsService;
		this.authenticationManager = authenticationManager;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		// @formatter:off
		http
				// enable cors
				.cors().and().requestMatchers().antMatchers("/oauth/**", "/*").and()
				// These from the above are secured by the following way
				.authorizeRequests().antMatchers("/", LOGIN).permitAll()
				// These from the rest are secured by the following way
				.anyRequest().authenticated().and()
				// Set login page
				.formLogin().loginPage(LOGIN).permitAll().defaultSuccessUrl(PROFILE)
				// Set logout handling
				.and().logout().logoutSuccessUrl(LOGOUT_SUCCESS);
		// @formatter:on

	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.parentAuthenticationManager(authenticationManager);
		auth.userDetailsService(userDetailsService);
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

}
