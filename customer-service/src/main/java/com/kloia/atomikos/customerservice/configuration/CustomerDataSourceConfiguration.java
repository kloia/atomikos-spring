package com.kloia.atomikos.customerservice.configuration;

import com.kloia.atomikos.core.configuration.AccountCoreDataSourceConfiguration;
import oracle.jdbc.xa.client.OracleXADataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableJpaRepositories(
        entityManagerFactoryRef = "CustomerDataSourceConfiguration",
        transactionManagerRef = "transactionManager",
        basePackages = {"com.kloia.atomikos.customerservice.repository.customer"}
)
public class CustomerDataSourceConfiguration {

    public Map<String, String> customerJpaProperties() {
        Map<String, String> customerJpaProperties = new HashMap<>();
        customerJpaProperties.put("hibernate.hbm2ddl.auto", "validate");
        customerJpaProperties.put("hibernate.dialect", "org.hibernate.dialect.Oracle10gDialect");
        customerJpaProperties.put("hibernate.show_sql", "true");
        customerJpaProperties.put("hibernate.temp.use_jdbc_metadata_defaults", "false");
        customerJpaProperties.put("hibernate.transaction.jta.platform", "com.atomikos.icatch.jta.hibernate4.AtomikosPlatform");
        customerJpaProperties.put("javax.persistence.transactionType", "JTA");
        return customerJpaProperties;
    }

    @Bean(name = "customerEntityManagerFactoryBuilder")
    @Primary
    public EntityManagerFactoryBuilder customerEntityManagerFactoryBuilder() {
        return new EntityManagerFactoryBuilder(
                new HibernateJpaVendorAdapter(), customerJpaProperties(), null
        );
    }


    @Bean(name = "CustomerDataSourceConfiguration")
    @Primary
    public LocalContainerEntityManagerFactoryBean getOracleEntityManager(
            @Qualifier("customerEntityManagerFactoryBuilder") EntityManagerFactoryBuilder customerEntityManagerFactoryBuilder,
            @Qualifier("customerDataSource") DataSource customerDataSource
    ) {
        return customerEntityManagerFactoryBuilder
                .dataSource(customerDataSource)
                .packages("com.kloia.atomikos.customerservice.model.customer")
                .persistenceUnit("oracle")
                .properties(customerJpaProperties())
                .jta(true)
                .build();
    }

    @Bean("customerDataSourceProperties")
    @Primary
    @ConfigurationProperties("datasource.customer")
    public DataSourceProperties customerDataSourceProperties() {
        return new DataSourceProperties();
    }


    @Bean("customerDataSource")
    @Primary
    @ConfigurationProperties("datasource.customer")
    public DataSource customerDataSource(@Qualifier("customerDataSourceProperties") DataSourceProperties customerDataSourceProperties) throws SQLException {
        // return customerDataSourceProperties.initializeDataSourceBuilder().build();
        OracleXADataSource ds = new OracleXADataSource();
        ds.setURL(customerDataSourceProperties.getUrl());
        ds.setUser(customerDataSourceProperties.getUsername());
        ds.setPassword(customerDataSourceProperties.getPassword());
        ds.setDatabaseName("C##CUSTOMER");

        AtomikosDataSourceBean xaDataSource = new AtomikosDataSourceBean();
        xaDataSource.setXaDataSource(ds);
        xaDataSource.setUniqueResourceName("xa_customer");
        return xaDataSource;
    }

    /*
    @Bean(name = "customerTransactionManager")
    public JpaTransactionManager transactionManager(@Qualifier("customerEntityManager") EntityManagerFactory customerEntityManager) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(customerEntityManager);
        return transactionManager;
    }
    */

}