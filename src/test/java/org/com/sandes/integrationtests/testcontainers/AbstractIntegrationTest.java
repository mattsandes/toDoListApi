package org.com.sandes.integrationtests.testcontainers;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.lifecycle.Startables;

import java.util.Map;
import java.util.stream.Stream;

@ContextConfiguration(initializers = AbstractIntegrationTest.Initializer.class)
public class AbstractIntegrationTest {

    public static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:16.2");

        private static void startContainer() {
            Startables.deepStart(Stream.of(postgreSQLContainer)).join();
        }

        private static Map<String, String> createConnectionConfiguration() {
            return Map.of(
                    "spring.datasource.url", postgreSQLContainer.getJdbcUrl(),
                    "spring.datasource.username", postgreSQLContainer.getUsername(),
                    "spring.datasource.password", postgreSQLContainer.getPassword());
        }

        @SuppressWarnings({"unchecked", "rawtypes"})
        public void initialize(ConfigurableApplicationContext applicationContext) {
            startContainer();

            ConfigurableEnvironment environment = applicationContext.getEnvironment();

            MapPropertySource testcontainer = new MapPropertySource(
                    "testcontainers",
                    (Map) createConnectionConfiguration());

            environment.getPropertySources().addFirst(testcontainer);
        }
    }
}
