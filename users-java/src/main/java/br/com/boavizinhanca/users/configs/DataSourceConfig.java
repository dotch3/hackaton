package br.com.boavizinhanca.users.configs;

import br.com.boavizinhanca.users.utils.Decrypt;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

import java.io.IOException;

public class DataSourceConfig {

    @Autowired
    ConfigUtility configUtility;

    @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;

    @Value("${spring.datasource.jdbcUrl}")
    private String url;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.hikari.pool-name}")
    private String poolName;

    @Value("${spring.datasource.hikari.minimum-idle}")
    private int minimumIdle;

    @Value("${spring.datasource.hikari.maximum-pool-size}")
    private int maximumPoolSize;

    @Value("${spring.datasource.hikari.connection-timeout}")
    private Long connectionTimeout;

    @Value("${spring.datasource.hikari.idle-timeout}")
    private Long idleTimeout;

    @Value("${spring.datasource.hikari.max-lifetime}")
    private Long maxLifetime;

    @Value("${crypto.password}")
    private String password;

    @Value("${crypto.publicKey}")
    private String publicKey;

    @Value("${crypto.algorithm}")
    private String algorithm;

    @Bean
    public HikariDataSource getDataSource() throws IOException {
        Decrypt decrypt = new Decrypt();
        HikariConfig config = new HikariConfig();

        config.setMinimumIdle(minimumIdle);
        config.setUsername(username);
        config.setMaximumPoolSize(maximumPoolSize);
        config.setPoolName(poolName);
        config.setConnectionTimeout(connectionTimeout);
        config.setIdleTimeout(idleTimeout);
        config.setMaxLifetime(maxLifetime);
        config.setJdbcUrl(url);
        config.setDriverClassName(driverClassName);
        config.setPassword(decrypt.decrypt(password, publicKey, algorithm));

        return new HikariDataSource(config);
    }
}
