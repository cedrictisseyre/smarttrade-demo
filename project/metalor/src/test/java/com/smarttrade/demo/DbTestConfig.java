package com.smarttrade.demo;

import com.smarttrade.demo.metalor.configuration.FlywayConfiguration;
import org.jetbrains.annotations.NotNull;
import org.springframework.boot.test.autoconfigure.jooq.JooqTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.support.TestPropertySourceUtils;
import org.testcontainers.containers.PostgreSQLContainer;


@TestConfiguration
@JooqTest
@Import({FlywayConfiguration.class})
public class DbTestConfig {
    public static final PostgreSQLContainer<?> postgresDbContainer = new PostgreSQLContainer<>("postgres").withCommand("postgres -c max_connections=20000");

    public static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        @Override
        public void initialize(@NotNull ConfigurableApplicationContext applicationContext) {
            postgresDbContainer.start();
            TestPropertySourceUtils.addInlinedPropertiesToEnvironment(
                applicationContext,
                "spring.datasource.url=" + postgresDbContainer.getJdbcUrl(),
                "spring.datasource.username=" + postgresDbContainer.getUsername(),
                "spring.datasource.password=" + postgresDbContainer.getPassword());
        }
    }
}
