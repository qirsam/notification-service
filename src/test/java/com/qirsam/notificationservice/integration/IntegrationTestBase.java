package com.qirsam.notificationservice.integration;

import com.qirsam.notificationservice.config.SpringConfig;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.containers.PostgreSQLContainer;

@Sql({
        "classpath:data_init.sql"
})
@ContextConfiguration(classes = {SpringConfig.class})
@ExtendWith(SpringExtension.class)
@Transactional
public abstract class IntegrationTestBase {
    private static final PostgreSQLContainer<?> container = new PostgreSQLContainer<>("postgres:14.5");

    @BeforeAll
    static void runContainer() {
        container.start();
    }

    @DynamicPropertySource
    static void postgresProperties(DynamicPropertyRegistry registry) {
        registry.add("hibernate.connection.url", container::getJdbcUrl);
    }
}
