package tn.esprit.boostra.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import tn.esprit.boostra.service.MyUserDetailsService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private MyUserDetailsService userDetailsService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
	}
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().mvcMatchers("/swagger-ui/**", "/configuration/**", "/swagger-resources/**", "/v2/api-docs");
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/registration").permitAll()
		.antMatchers("/post/**").access("hasRole('employe') ")
		.antMatchers("/getUserById/{idUser}").access("hasRole('administrator') ")
		.antMatchers("/say**").access("hasRole('administrator') or hasRole('moderator') ")
		.antMatchers("/getAll").access("hasRole('administrator')")
		.antMatchers("/event/add-event").access("hasRole('organizer')")
		.anyRequest().authenticated().and().httpBasic().and().csrf().disable();
	}
}
