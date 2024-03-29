package br.com.alura.mvc.mudi.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter  {
	
	@Autowired
	private SecurityDatabaseService securityService;
	
	@Autowired
	public void globalUserDetails(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(securityService).passwordEncoder(NoOpPasswordEncoder.getInstance());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
			.antMatchers("/").permitAll()
			.antMatchers(HttpMethod.POST, "/login").permitAll()
			.antMatchers(HttpMethod.GET, "/home").hasAnyRole("USERS", "ADMINS")
			.antMatchers(HttpMethod.GET, "/home/{status}").hasAnyRole("USERS", "ADMINS")
			.antMatchers(HttpMethod.GET, "/pedidos").hasAnyRole("USERS", "ADMINS")
			.antMatchers(HttpMethod.POST, "/pedido/formulario").hasAnyRole("ADMINS")
			.antMatchers(HttpMethod.POST, "/pedido/novo").hasAnyRole("ADMINS")
			.anyRequest().authenticated().and().formLogin(form -> form
					.loginPage("/login")
					.defaultSuccessUrl("/home")
					.permitAll());
	}
	
}
