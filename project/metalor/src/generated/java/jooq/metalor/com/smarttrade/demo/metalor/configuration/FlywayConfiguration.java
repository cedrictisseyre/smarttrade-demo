package com.smarttrade.demo.metalor.configuration;

import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.migration.JavaMigration;
import org.springframework.boot.autoconfigure.flyway.FlywayMigrationStrategy;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FlywayConfiguration {

    private final ApplicationContext applicationContext;

    public FlywayConfiguration(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Bean
    public FlywayMigrationStrategy flywayMigrationStrategy() {
        return flyway -> {
            var dataSource = flyway.getConfiguration().getDataSource();
            Flyway.configure()
                .schemas("metalor")
                .locations("db/migration")
                .dataSource(dataSource)
                .javaMigrations(applicationContext.getBeansOfType(JavaMigration.class)
                    .values()
                    .toArray(new JavaMigration[0]))
                .load()
                .migrate();
        };
    }
}
