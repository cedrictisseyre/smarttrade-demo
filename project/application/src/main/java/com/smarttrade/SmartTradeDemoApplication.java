package com.smarttrade;

import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.gson.GsonAutoConfiguration;
import org.springframework.boot.autoconfigure.r2dbc.R2dbcAutoConfiguration;
import org.springframework.boot.autoconfigure.thymeleaf.ThymeleafAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

import java.util.logging.LogManager;

@SpringBootApplication(exclude = {GsonAutoConfiguration.class, ThymeleafAutoConfiguration.class, R2dbcAutoConfiguration.class})
@ConfigurationPropertiesScan({"com.smarttrade.demo.core"})
public class SmartTradeDemoApplication {

    // Some standard librairies of the market has been used here for demo purpose
    // - Gson for json serialization/deserialization
    // - Jooq (and Hikari) for database access
    // - Flyway for database migration
    // - Flogger for logs
    // - Swagger for openApi documentation
    public static void main(String[] args) {
        LogManager.getLogManager().reset();
        SLF4JBridgeHandler.install();
        SpringApplication.run(SmartTradeDemoApplication.class, args);
    }
}
