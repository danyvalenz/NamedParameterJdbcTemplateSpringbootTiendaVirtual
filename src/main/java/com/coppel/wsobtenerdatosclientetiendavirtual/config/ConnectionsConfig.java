/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.coppel.wsobtenerdatosclientetiendavirtual.config;

import javax.sql.DataSource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

/**
 *
 * @author daniel.valenzuela
 */
@Configuration
public class ConnectionsConfig {
    @Autowired
    Environment env;

    @Bean(name = "dsTiendaVirtual")
    @Primary
    public DataSource conexionTiendaVirtualDataSource() {

        HikariConfig hikariConfig = new HikariConfig();

        hikariConfig.setJdbcUrl(env.getProperty("tiendavirtual.datasource.jdbc-url"));
        hikariConfig.setPassword(env.getProperty("tiendavirtual.datasource.password"));
        hikariConfig.setUsername(env.getProperty("tiendavirtual.datasource.username"));
        hikariConfig.setConnectionTimeout(env.getProperty("spring.datasource.hikari.connectionTimeout", Long.class));
        hikariConfig.setIdleTimeout(env.getProperty("spring.datasource.hikari.idleTimeout", Long.class));
        hikariConfig.setMaxLifetime(env.getProperty("spring.datasource.hikari.maxLifetime", Long.class));
        hikariConfig.setMaximumPoolSize(env.getProperty("spring.datasource.hikari.maximumPoolSize", Integer.class));
        hikariConfig.setMinimumIdle(env.getProperty("spring.datasource.hikari.minimumIdle", Integer.class));
        return new HikariDataSource(hikariConfig);
    }

    
    @Bean(name = "jdbcTiendaVirtual")
    @Autowired
    public NamedParameterJdbcTemplate jdbcTiendaVirtual(@Qualifier("dsTiendaVirtual") DataSource dsTiendaVirtual) {
        return new NamedParameterJdbcTemplate(dsTiendaVirtual);
    }

    @Bean(name = "dsTienda800")
    @Primary
    public DataSource conexionTienda800DataSource() {

        HikariConfig hikariConfig = new HikariConfig();

        hikariConfig.setJdbcUrl(env.getProperty("tienda800.datasource.jdbc-url"));
        hikariConfig.setPassword(env.getProperty("tienda800.datasource.password"));
        hikariConfig.setUsername(env.getProperty("tienda800.datasource.username"));
        hikariConfig.setConnectionTimeout(env.getProperty("spring.datasource.hikari.connectionTimeout", Long.class));
        hikariConfig.setIdleTimeout(env.getProperty("spring.datasource.hikari.idleTimeout", Long.class));
        hikariConfig.setMaxLifetime(env.getProperty("spring.datasource.hikari.maxLifetime", Long.class));
        hikariConfig.setMaximumPoolSize(env.getProperty("spring.datasource.hikari.maximumPoolSize", Integer.class));
        hikariConfig.setMinimumIdle(env.getProperty("spring.datasource.hikari.minimumIdle", Integer.class));
        return new HikariDataSource(hikariConfig);
    }


    @Bean(name = "jdbcTienda800")
    @Autowired
    public NamedParameterJdbcTemplate jdbcTienda800(@Qualifier("dsTienda800") DataSource dsTienda800) {
        return new NamedParameterJdbcTemplate(dsTienda800);
    }
}
