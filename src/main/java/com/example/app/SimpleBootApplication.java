package com.example.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.naming.NamingException;
import javax.sql.DataSource;
import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;
import org.apache.tomcat.util.descriptor.web.ContextResource;
import org.springframework.jndi.JndiObjectFactoryBean;

@Configuration
@ComponentScan(basePackages = { "com.example.app" })
@EnableAutoConfiguration(exclude = { HibernateJpaAutoConfiguration.class, JpaRepositoriesAutoConfiguration.class,
		DataSourceAutoConfiguration.class })

@SpringBootApplication
public class SimpleBootApplication extends SpringBootServletInitializer{

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(SimpleBootApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(SimpleBootApplication.class, args);
	}

	@Bean
	public TomcatEmbeddedServletContainerFactory tomcatFactory() {
		return new TomcatEmbeddedServletContainerFactory() {
			@Override
			protected TomcatEmbeddedServletContainer getTomcatEmbeddedServletContainer(Tomcat tomcat) {
				tomcat.enableNaming();
				return super.getTomcatEmbeddedServletContainer(tomcat);
			}

			@Override
			protected void postProcessContext(Context context) {
				// Adding the JNDI Details for MySql
				ContextResource r = new ContextResource();
				r.setName("jdbc/MySQL");
				r.setType(DataSource.class.getName());
				r.setProperty("factory", "org.apache.tomcat.jdbc.pool.DataSourceFactory");
				r.setProperty("driverClassName", "com.mysql.jdbc.Driver");
//				r.setProperty("url", "jdbc:mysql://localhost:3306/springbootdb?useSSL=false");
				r.setProperty("url", "jdbc:mysql://database:3306/springbootdb?useSSL=false");
				r.setProperty("username", "root");
				r.setProperty("password", "pratama");
				context.getNamingResources().addResource(r);
			}
		};
	}
	
	@Lazy
	@Bean(destroyMethod = "")
	public DataSource mySqlJNDIDataSource() throws IllegalArgumentException, NamingException {
		JndiObjectFactoryBean bean = new JndiObjectFactoryBean();
		bean.setJndiName("java:comp/env/jdbc/MySQL");
		bean.setProxyInterface(DataSource.class);
		bean.setLookupOnStartup(false);
		bean.afterPropertiesSet();
		return (DataSource) bean.getObject();
	}
	
	@Bean
	@Qualifier("mySqljdbcTemplate")
	public JdbcTemplate mySqljdbcTemplate() throws IllegalArgumentException, NamingException{
		return new JdbcTemplate(mySqlJNDIDataSource());
	}
	
}

















