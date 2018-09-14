package config;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication()
			.passwordEncoder(new BCryptPasswordEncoder())
			.withUser("a").password("pass").roles("ADMIN").and()
			.withUser("b").password("b").roles("Trainer"); 
	}
	
	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
	
		httpSecurity
				.authorizeRequests()
				//.antMatchers
				.anyRequest()
				.fullyAuthenticated()
				.and().httpBasic();
		httpSecurity.csrf().disable();//allows everything coming into the application with permitAll()
		//no security at the moment but still controlling the spring security
	}
	
}
