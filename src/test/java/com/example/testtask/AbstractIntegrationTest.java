package com.example.testtask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.lifecycle.Startables;
import org.testcontainers.utility.TestcontainersConfiguration;

import javax.sql.DataSource;
import java.util.stream.Stream;

import static java.lang.String.format;

@SpringBootTest(properties = {"spring.main.allow-bean-definition-overriding=true"})
public class AbstractIntegrationTest {
    @Autowired
    @Qualifier("dataSource")
    DataSource dataSource;

    public static GenericContainer postgres = new PostgreSQLContainer("postgres:11.3")
            .withReuse(TestcontainersConfiguration.getInstance().environmentSupportsReuse());

    static {
        Startables.deepStart(Stream.of(postgres)).join();
        System.setProperty("spring.datasource.url", format("jdbc:postgresql://%s:%d/test", postgres.getContainerIpAddress(), postgres.getMappedPort(5432)));
    }
}
