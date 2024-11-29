package co.com.amrsoftware.msvc_franchise.config;

import co.com.amrsoftware.msvc_franchise.infrastructure.repository.config.R2dbcPropertyConfig;
import io.asyncer.r2dbc.mysql.MySqlConnectionConfiguration;
import io.asyncer.r2dbc.mysql.MySqlConnectionFactory;
import io.r2dbc.pool.ConnectionPool;
import io.r2dbc.pool.ConnectionPoolConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class MysqlConnectionConfig {
    @Value("${db.database}")
    private String database;
    @Value("${db.host-name}")
    private String host;
    @Value("${db.max-size}")
    private Integer maxSize;
    @Value("${db.max-live-connection}")
    private Integer maxLiveDuration;
    @Value("${db.min-size}")
    private Integer minSizee;
    @Value("${db.password}")
    private String password;
    @Value("${db.port}")
    private Integer port;
    @Value("${db.username}")
    private String username;

    @Bean
    public ConnectionPool connectionPool() {
        var mysqlConnectionConfig = MySqlConnectionConfiguration.builder()
            .database(getProperty().getDatabase())
            .host(getProperty().getHostName())
            .password(getProperty().getPassword())
            .port(getProperty().getPort())
            .username(getProperty().getUsername())
            .build();

        var config = ConnectionPoolConfiguration.builder()
            .connectionFactory(MySqlConnectionFactory.from(mysqlConnectionConfig))
            .name("mysql-connection")
            .maxIdleTime(Duration.ofMinutes(maxLiveDuration))
            .maxSize(maxSize)
            .minIdle(minSizee)
            .build();

        return new ConnectionPool(config);
    }

    private R2dbcPropertyConfig getProperty() {
        return R2dbcPropertyConfig.builder()
            .database(database)
            .hostName(host)
            .password(password)
            .port(port)
            .username(username)
            .build();
    }
}
