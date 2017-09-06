package pl.kamsoft.nfz.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import pl.kamsoft.nfz.utility.GetPropertyValues;

@Configuration
@ComponentScan({"staz", "dao"})
public class Config {
	@Bean
	public GetPropertyValues getPropertyValues() {
		return new GetPropertyValues();
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean(name="dataSource")
	public DriverManagerDataSource dataSource() {
		DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
		driverManagerDataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
		driverManagerDataSource.setUrl("jdbc:oracle:thin:@GLORA2.kamsoft.local:1521/SZKOL");
		driverManagerDataSource.setUsername("PWOJTASINSKI");
		driverManagerDataSource.setPassword("wojtasinski");
		
		return driverManagerDataSource;
	}
}
