package com.backend.infrastructure.controller.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Configuration class for database-related settings.
 */
@Configuration
@EntityScan(basePackages = {"com.backend.data.models"})
@EnableJpaRepositories(basePackages = {"com.backend.data.repository"})
public class DBConfig {
}
