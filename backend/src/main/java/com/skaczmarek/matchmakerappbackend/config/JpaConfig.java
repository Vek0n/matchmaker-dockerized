package com.skaczmarek.matchmakerappbackend.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.sql.DataSource;


//@Configuration
//@ComponentScan({"com.skaczmarek.matchmakerappbackend"})
//@EntityScan(basePackages = "com.skaczmarek.matchmakerappbackend")
//@EnableJpaRepositories(basePackages = "com.skaczmarek.matchmakerappbackend")
//public class JpaConfig {
//    @Bean
//    public DataSource getDataSource()
//    {
//        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
//        dataSourceBuilder.driverClassName("org.h2.Driver");
//        dataSourceBuilder.url("jdbc:h2:mem:szymon");
//        dataSourceBuilder.username("szymon");
//        dataSourceBuilder.password("");
//        return dataSourceBuilder.build();
//    }
//}
