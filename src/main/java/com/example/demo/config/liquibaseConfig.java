package com.example.demo.config;

import javax.sql.DataSource;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;


import liquibase.integration.spring.SpringLiquibase;

@Component
public class liquibaseConfig{

    @Bean   
    public SpringLiquibase liquibase(DataSource dataSource){
        
        SpringLiquibase liquibase = new SpringLiquibase();

        liquibase.setChangeLog("file:src/main/resources/db/changelog/db.changelog-master.xml");
        liquibase.setDataSource(dataSource);
        return liquibase;
    }
}