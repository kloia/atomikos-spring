package com.kloia.atomikos.core.configuration;

import oracle.jdbc.xa.client.OracleXADataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
        entityManagerFactoryRef = "AccountCoreDataSourceConfiguration",
        transactionManagerRef = "transactionManager",
        basePackages = {"com.kloia.atomikos.core.repository.account"}
)
public class AccountCoreDataSourceConfiguration {

    public Map<String, String> accountJpaProperties() {
        Map<String, String> accountJpaProperties = new HashMap<>();
        accountJpaProperties.put("hibernate.hbm2ddl.auto", "validate");
        accountJpaProperties.put("hibernate.dialect", "org.hibernate.dialect.Oracle10gDialect");
        accountJpaProperties.put("hibernate.show_sql", "true");
        accountJpaProperties.put("hibernate.temp.use_jdbc_metadata_defaults", "false");
        accountJpaProperties.put("hibernate.transaction.jta.platform", "com.atomikos.icatch.jta.hibernate4.AtomikosPlatform");
        accountJpaProperties.put("javax.persistence.transactionType", "JTA");
        return accountJpaProperties;
    }


    @Bean(name = "accountCoreEntityManagerFactoryBuilder")
    //@Primary
    public EntityManagerFactoryBuilder accountEntityManagerFactoryBuilder() {
        return new EntityManagerFactoryBuilder(
                new HibernateJpaVendorAdapter(), accountJpaProperties(), null
        );
    }


    @Bean(name = "AccountCoreDataSourceConfiguration")
    //@Primary
    public LocalContainerEntityManagerFactoryBean accountEntityManager(
            @Qualifier("accountCoreEntityManagerFactoryBuilder") EntityManagerFactoryBuilder accountEntityManagerFactoryBuilder,
            @Qualifier("accountCoreDataSource") DataSource accountCoreDataSource
    ) {
        return accountEntityManagerFactoryBuilder
                .dataSource(accountCoreDataSource)
                .packages("com.kloia.atomikos.core.model.account")
                .persistenceUnit("oracle")
                .properties(accountJpaProperties())
                .jta(true)
                .build();
    }

    @Bean("accountCoreDataSourceProperties")
    //@Primary
    @ConfigurationProperties("datasource.account")
    public DataSourceProperties accountCoreDataSourceProperties() {
        return new DataSourceProperties();
    }


    @Bean("accountCoreDataSource")
    //@Primary
    @ConfigurationProperties("datasource.account")
    public DataSource accountCoreDataSource(@Qualifier("accountCoreDataSourceProperties") DataSourceProperties accountCoreDataSourceProperties) throws SQLException {
        // return accountDataSourceProperties.initializeDataSourceBuilder().build();
        OracleXADataSource ds = new OracleXADataSource();
        ds.setURL(accountCoreDataSourceProperties.getUrl());
        ds.setUser(accountCoreDataSourceProperties.getUsername());
        ds.setPassword(accountCoreDataSourceProperties.getPassword());
        ds.setDatabaseName("C##ACCOUNT");

        AtomikosDataSourceBean xaDataSource = new AtomikosDataSourceBean();
        xaDataSource.setXaDataSource(ds);
        xaDataSource.setUniqueResourceName("xa_core_account");
        xaDataSource.setMinPoolSize(4);
        xaDataSource.setMaxPoolSize(8);
        xaDataSource.setMaxIdleTime(10);
        return xaDataSource;
    }

    /*
    @Bean(name = "accountTransactionManager")
    public JpaTransactionManager transactionManager(@Qualifier("accountEntityManager") EntityManagerFactory accountEntityManager) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(accountEntityManager);
        return transactionManager;
    }
    */

}