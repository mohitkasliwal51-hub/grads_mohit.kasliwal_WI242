package com.mohit.assignment_student_web_page.config;

// import javax.sql.DataSource;

// import org.springframework.beans.factory.annotation.Qualifier;
// import org.springframework.boot.context.properties.ConfigurationProperties;
// import org.springframework.boot.jdbc.autoconfigure.DataSourceProperties;
// import org.springframework.boot.jpa.EntityManagerFactoryBuilder;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
// import org.springframework.orm.jpa.JpaTransactionManager;
// import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

// @Configuration
// @EnableJpaRepositories(basePackages = "com.mohit.assignment_student_web_page.repository.postgres", entityManagerFactoryRef = "postgresEntityManagerFactory", transactionManagerRef = "postgresTransactionManager")
// public class PostgresConfig {

//     @Bean
//     @ConfigurationProperties("spring.datasource.postgres")
//     public DataSourceProperties postgresDataSourceProperties() {
//         return new DataSourceProperties();
//     }

//     @Bean
//     public DataSource postgresDataSource() {
//         return postgresDataSourceProperties()
//                 .initializeDataSourceBuilder()
//                 .build();
//     }

//     @Bean(name = "postgresEntityManagerFactory")
//     public LocalContainerEntityManagerFactoryBean postgresEntityManagerFactory(
//             EntityManagerFactoryBuilder builder) {
//         return builder
//                 .dataSource(postgresDataSource())
//                 .packages("com.mohit.assignment_student_web_page.entity")
//                 .persistenceUnit("postgres")
//                 .build();
//     }

//     @Bean(name = "postgresTransactionManager")
//     public JpaTransactionManager postgresTransactionManager(
//             @Qualifier("postgresEntityManagerFactory") jakarta.persistence.EntityManagerFactory entityManagerFactory) {
//         return new JpaTransactionManager(entityManagerFactory);
//     }
// }

import java.util.HashMap;

import javax.sql.DataSource;
import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
		entityManagerFactoryRef = "psqlEntityManagerFactory", 
		transactionManagerRef = "psqlTransactionManager", 
		basePackages = {
				"com.mohit.assignment_student_web_page.repository.postgres"
		}
)
public class postgreConfig {

	@Bean(name = "psqlDataSource")
	@ConfigurationProperties(prefix = "spring.datasource.postgres")
	public DataSource dataSource() {
		return DataSourceBuilder.create().build();
	}

	@Bean(name = "psqlEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean psqlEntityManagerFactory(EntityManagerFactoryBuilder builder,
			@Qualifier("psqlDataSource") DataSource dataSource) {
		
		HashMap<String, Object> properties = new HashMap<>();
		properties.put("hibernate.hbm2ddl.auto", "update");
		
		return builder.dataSource(dataSource)
					  .properties(properties)
					  .packages("com.mohit.assignment_student_web_page.entity")
					  .persistenceUnit("psql")
					  .build();
	}

	@Bean(name = "psqlTransactionManager")
	public PlatformTransactionManager psqlTransactionManager(@Qualifier("psqlEntityManagerFactory") EntityManagerFactory psqlEntityManagerFactory) {
		return new JpaTransactionManager(psqlEntityManagerFactory);
	}
}