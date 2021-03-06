package com.kloia.atomikos.configuration;

import org.postgresql.xa.PGXADataSource;
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
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableJpaRepositories(
        entityManagerFactoryRef = "AccountDataSourceConfiguration",
        transactionManagerRef = "transactionManager",
        basePackages = {"com.kloia.atomikos.repository.account"}
)
public class AccountDataSourceConfiguration {

    public Map<String, String> accountJpaProperties() {
        Map<String, String> accountJpaProperties = new HashMap<>();
        accountJpaProperties.put("hibernate.hbm2ddl.auto", "validate");
        accountJpaProperties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        accountJpaProperties.put("hibernate.show_sql", "true");
        accountJpaProperties.put("hibernate.temp.use_jdbc_metadata_defaults", "false");
        accountJpaProperties.put("hibernate.transaction.jta.platform", "com.atomikos.icatch.jta.hibernate4.AtomikosPlatform");
        accountJpaProperties.put("javax.persistence.transactionType", "JTA");
        return accountJpaProperties;
    }


    @Bean(name = "accountEntityManagerFactoryBuilder")
//    @Primary
    public EntityManagerFactoryBuilder accountEntityManagerFactoryBuilder() {
        return new EntityManagerFactoryBuilder(
                new HibernateJpaVendorAdapter(), accountJpaProperties(), null
        );
    }


    @Bean(name = "AccountDataSourceConfiguration")
//    @Primary
    public LocalContainerEntityManagerFactoryBean accountEntityManager(
            @Qualifier("accountEntityManagerFactoryBuilder") EntityManagerFactoryBuilder accountEntityManagerFactoryBuilder,
            @Qualifier("accountDataSource") DataSource postgresDataSource
    ) {
        return accountEntityManagerFactoryBuilder
                .dataSource(postgresDataSource)
                .packages("com.kloia.atomikos.model.account")
                .persistenceUnit("postgres")
                .properties(accountJpaProperties())
                .jta(true)
                .build();
    }

    @Bean("accountDataSourceProperties")
//    @Primary
    @ConfigurationProperties("datasource.account")
    public DataSourceProperties accountDataSourceProperties() {
        return new DataSourceProperties();
    }


    @Bean("accountDataSource")
//    @Primary
    @ConfigurationProperties("datasource.account")
    public DataSource accountDataSource(@Qualifier("accountDataSourceProperties") DataSourceProperties accountDataSourceProperties) {
        // return accountDataSourceProperties.initializeDataSourceBuilder().build();
        PGXADataSource ds = new PGXADataSource();
        ds.setUrl(accountDataSourceProperties.getUrl());
        ds.setUser(accountDataSourceProperties.getUsername());
        ds.setPassword(accountDataSourceProperties.getPassword());

        AtomikosDataSourceBean xaDataSource = new AtomikosDataSourceBean();
        xaDataSource.setXaDataSource(ds);
        xaDataSource.setUniqueResourceName("xa_account");
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