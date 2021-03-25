package com.kloia.atomikos.customerservice.configuration;

import oracle.jdbc.xa.client.OracleXADataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableJpaRepositories(
        entityManagerFactoryRef = "CustomerAddressDataSourceConfiguration",
        transactionManagerRef = "transactionManager",
        basePackages = {"com.kloia.atomikos.customerservice.repository.customeraddress"}
)
public class CustomerAddressDataSourceConfiguration {

    public Map<String, String> customerAddressJpaProperties() {
        Map<String, String> customerAddressJpaProperties = new HashMap<>();
        customerAddressJpaProperties.put("hibernate.hbm2ddl.auto", "validate");
        customerAddressJpaProperties.put("hibernate.dialect", "org.hibernate.dialect.Oracle10gDialect");
        customerAddressJpaProperties.put("hibernate.show_sql", "true");
        customerAddressJpaProperties.put("hibernate.temp.use_jdbc_metadata_defaults", "false");
        customerAddressJpaProperties.put("hibernate.transaction.jta.platform", "com.atomikos.icatch.jta.hibernate4.AtomikosPlatform");
        customerAddressJpaProperties.put("javax.persistence.transactionType", "JTA");
        return customerAddressJpaProperties;
    }

    @Bean(name = "customerAddressEntityManagerFactoryBuilder")
    //@Primary
    public EntityManagerFactoryBuilder customerAddressEntityManagerFactoryBuilder() {
        return new EntityManagerFactoryBuilder(
                new HibernateJpaVendorAdapter(), customerAddressJpaProperties(), null
        );
    }


    @Bean(name = "CustomerAddressDataSourceConfiguration")
    //@Primary
    public LocalContainerEntityManagerFactoryBean getOracleEntityManager(
            @Qualifier("customerAddressEntityManagerFactoryBuilder") EntityManagerFactoryBuilder customerEntityManagerFactoryBuilder,
            @Qualifier("customerAddressDataSource") DataSource customerDataSource
    ) {
        return customerEntityManagerFactoryBuilder
                .dataSource(customerDataSource)
                .packages("com.kloia.atomikos.customerservice.model.customeraddress")
                .persistenceUnit("oracle")
                .properties(customerAddressJpaProperties())
                .jta(true)
                .build();
    }

    @Bean("customerAddressDataSourceProperties")
    //@Primary
    @ConfigurationProperties("datasource.customer")
    public DataSourceProperties customerAddressDataSourceProperties() {
        return new DataSourceProperties();
    }


    @Bean("customerAddressDataSource")
    //@Primary
    @ConfigurationProperties("datasource.customer")
    public DataSource customerAddressDataSource(@Qualifier("customerAddressDataSourceProperties") DataSourceProperties customerAddressDataSourceProperties) throws SQLException {
        // return customerDataSourceProperties.initializeDataSourceBuilder().build();
        OracleXADataSource ds = new OracleXADataSource();
        ds.setURL(customerAddressDataSourceProperties.getUrl());
        ds.setUser(customerAddressDataSourceProperties.getUsername());
        ds.setPassword(customerAddressDataSourceProperties.getPassword());
        ds.setDatabaseName("C##CUSTOMER");

        AtomikosDataSourceBean xaDataSource = new AtomikosDataSourceBean();
        xaDataSource.setXaDataSource(ds);
        xaDataSource.setUniqueResourceName("xa_customer_address");
        xaDataSource.setMinPoolSize(4);
        xaDataSource.setMaxPoolSize(8);
        xaDataSource.setMaxIdleTime(10);
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