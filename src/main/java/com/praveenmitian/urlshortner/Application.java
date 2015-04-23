package com.praveenmitian.urlshortner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

@SpringBootApplication
public class Application {

	@Bean
	JdbcTemplate getJdbcTemplate() {
		SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
		dataSource.setDriver(new org.apache.derby.jdbc.ClientDriver());
		String dbName = "urlshortner";
		String connectionURL = "jdbc:derby://172.31.29.133:1527/" + dbName
				+ ";create=true";
		dataSource.setUrl(connectionURL);
		dataSource.setUsername("SA");
		dataSource.setPassword("SA");
		// dataSource.setUrl("jdbc:h2:file:D:/data/urlshortner");
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		// jdbcTemplate.execute("drop table url if exists");
		// jdbcTemplate.execute("create table url("
		// + "longUrl varchar(500), shortUrl varchar(255))");
		return jdbcTemplate;
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}