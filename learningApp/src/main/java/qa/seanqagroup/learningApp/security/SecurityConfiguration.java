package qa.seanqagroup.learningApp.security;


import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        securedEnabled = true,
        jsr250Enabled = true,
        prePostEnabled = true
)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	
	
	  @Override
	    protected void configure(HttpSecurity http) throws Exception {
	        http
	                .authorizeRequests()
	                    .antMatchers("/",
	                        "/favicon.ico",
	                        "/**/*.png",
	                        "/**/*.gif",
	                        "/**/*.svg",
	                        "/**/*.jpg",
	                        "/**/*.html",
	                        "/**/*.css",
	                        "/**/*.js")
	                        .permitAll()
	                    .antMatchers(HttpMethod.POST,"/ucc/register","/ucc/login")
	                        .permitAll()
	                    .antMatchers("/ucc/u", "/courses/getcourses")
	                        .permitAll()
	                    .antMatchers(HttpMethod.GET, "/ucc/u","/ucc/u/id/{id}","/ucc/u/e/{e}")
	                        .permitAll()
	                    .anyRequest()
	                        .authenticated();
}
}