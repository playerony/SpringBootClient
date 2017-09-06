package pl.kamsoft.nfz.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private DataSource dataSource;
	
	@Autowired
	public SecurityConfig(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication()
		.dataSource(dataSource)
		.passwordEncoder(new BCryptPasswordEncoder())
		.usersByUsernameQuery("SELECT u.USER_NAME, u.PASSWORD, u.ROLE_ID FROM USERS u, ROLES r WHERE r.ID=u.ROLE_ID AND u.USER_NAME=?")
		.authoritiesByUsernameQuery("SELECT u.USER_NAME, r.ROLE_NAME FROM USERS u, ROLES r where r.ID=u.ROLE_ID and u.USER_NAME=?");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/login").permitAll().antMatchers("/rest/**").permitAll()
			.antMatchers("/book/").hasAnyAuthority("SUPER_ADMIN", "ADMIN", "USER")
			.antMatchers("/book/add").hasAnyAuthority("SUPER_ADMIN", "ADMIN")
			.antMatchers("/book/remove").hasAnyAuthority("SUPER_ADMIN", "ADMIN")
			.antMatchers("/book/edit").hasAnyAuthority("SUPER_ADMIN", "ADMIN")
			.antMatchers("/author/**").hasAnyAuthority("SUPER_ADMIN", "ADMIN", "USER")
			.antMatchers("/author/add").hasAnyAuthority("SUPER_ADMIN", "ADMIN")
			.antMatchers("/author/remove").hasAnyAuthority("SUPER_ADMIN", "ADMIN")
			.antMatchers("/author/edit").hasAnyAuthority("SUPER_ADMIN", "ADMIN")
			.and().formLogin()
			.loginPage("/login").failureUrl("/login?error=true").defaultSuccessUrl("/book/")
				.usernameParameter("username")
				.passwordParameter("password")
			.and().logout()
			.logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login")
			.and().exceptionHandling().accessDeniedPage("/access-denied");
		
		http.csrf().disable();
	}
	
	

	@Override
	public void configure(WebSecurity web) throws Exception
	{
		web.ignoring().antMatchers("/resource/**", "/static/**");
	}
	
}