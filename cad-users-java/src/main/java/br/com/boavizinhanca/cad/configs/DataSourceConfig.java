package br.com.boavizinhanca.cad.configs;

import br.com.boavizinhanca.cad.utils.CryptoUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;

@Configuration
@PropertySource("classpath:application.properties")
public class DataSourceConfig {

    @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;

    @Value("${spring.datasource.jdbcUrl}")
    private String url;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${crypto.password}")
    private String password;

    @Value("${crypto.publicKey}")
    private String publicKey;

    @Value("${crypto.algorithm}")
    private String algorithm;

    @Bean
    public DataSource getDataSource() {

        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName(driverClassName);
        dataSourceBuilder.url(url);
        dataSourceBuilder.username(username);
        dataSourceBuilder.password(CryptoUtils.decrypt(password, publicKey, algorithm));
        return dataSourceBuilder.build();
    }
}
