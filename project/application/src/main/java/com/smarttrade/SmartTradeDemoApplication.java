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

    public static void main(String[] args) {
        LogManager.getLogManager().reset();
        SLF4JBridgeHandler.install();
        SpringApplication.run(SmartTradeDemoApplication.class, args);
    }
}
