package ru.axiomatika.CreditApp.config;

import jakarta.annotation.PreDestroy;
import lombok.RequiredArgsConstructor;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import ru.axiomatika.CreditApp.model.Client;
import ru.axiomatika.CreditApp.model.Contract;
import ru.axiomatika.CreditApp.model.EmploymentInfo;
import ru.axiomatika.CreditApp.model.Request;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@RequiredArgsConstructor
public class HibernateConfig {

    private final Environment env;

    private SessionFactory sessionFactory;


    @Bean
    public SessionFactory sessionFactory(DataSource dataSource) {
        if (sessionFactory == null) {
            try {
                StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(hibernateProperties(dataSource))
                        .build();

                Metadata metadata = new MetadataSources(standardRegistry)
                        .addAnnotatedClass(Client.class)
                        .addAnnotatedClass(Request.class)
                        .addAnnotatedClass(Contract.class)
                        .addAnnotatedClass(EmploymentInfo.class)
                        .getMetadataBuilder()
                        .build();

                sessionFactory = metadata.getSessionFactoryBuilder().build();

            } catch (Exception e) {
                throw new RuntimeException("Ошибка создания SessionFactory", e);
            }
        }
        return sessionFactory;
    }


    private Properties hibernateProperties(DataSource dataSource) {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", env.getProperty("spring.jpa.properties.hibernate.dialect"));
        properties.put("hibernate.hbm2ddl.auto", env.getProperty("spring.jpa.hibernate.ddl-auto"));
        properties.put("hibernate.show_sql", env.getProperty("spring.jpa.show-sql"));
        properties.put("hibernate.connection.datasource", dataSource);
        return properties;
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl(env.getProperty("spring.datasource.url"));
        dataSource.setUsername(env.getProperty("spring.datasource.username"));
        dataSource.setPassword(env.getProperty("spring.datasource.password"));
        return dataSource;
    }

    @PreDestroy
    public void closeSessionFactory() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }
}
