/**
 * 
 */
package com.example.mvc.configuration;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @author amit
 * This is application java config class to define beans for middle and backend of web application.
 * This config class is used by ContextLoaderListener to create one of two application contexts in spring mvc web application.
 *
 */

@Configuration
@ComponentScan(basePackages = {"com.example.mvc"}, excludeFilters = @Filter(type = FilterType.ANNOTATION, classes = EnableWebMvc.class))
public class RootJavaConfig {
	// middle-tier bean configuration will be here
	
	/**
	 * Configure embedded database to be used for authentication as use data provider
	 * @return
	 */
	@Bean
	public DataSource dataSource() {
		EmbeddedDatabaseBuilder embeddedDatabaseBuilder = new EmbeddedDatabaseBuilder();
		embeddedDatabaseBuilder.setType(EmbeddedDatabaseType.HSQL) // this is actually default
		.addScripts("db/sql/user-details-create.sql") // script for creating spring security db schema
		.addScripts("db/sql/user-details-insert.sql"); // script for hard coded users
		
		return embeddedDatabaseBuilder.build();
	}
}
	