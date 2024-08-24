package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.sql.DataSource;
import java.util.Objects;

/**
 * Application configuration class for setting up Spring application context.
 * This class configures web MVC settings, component scanning, aspect-oriented programming, and data source properties.
 */
@Configuration
@EnableWebMvc
@EnableAspectJAutoProxy
@ComponentScan(basePackages = "org.example")
@PropertySource("classpath:application.properties")
public class AppConfig {
    private final Environment environment;

    /**
     * Constructor with dependency injection.
     *
     * @param environment the environment used to access application properties.
     */
    @Autowired
    public AppConfig(Environment environment) {
        this.environment = environment;
    }

    /**
     * Configures the data source for connecting to the database.
     *
     * @return a {@link DataSource} instance configured with properties from the environment.
     */
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(Objects.requireNonNull(environment.getProperty("spring.datasource.driver-class-name")));
        dataSource.setUrl(environment.getProperty("spring.datasource.url"));
        dataSource.setUsername(environment.getProperty("spring.datasource.username"));
        dataSource.setPassword(environment.getProperty("spring.datasource.password"));
        return dataSource;
    }
}
