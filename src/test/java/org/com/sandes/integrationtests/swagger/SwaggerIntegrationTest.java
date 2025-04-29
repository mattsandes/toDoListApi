package org.com.sandes.integrationtests.swagger;

import org.com.sandes.integrationtests.testcontainers.AbstractIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class SwaggerIntegrationTest extends AbstractIntegrationTest {

    @Test
    public void shouldDisplaySwaggerUiPage() {
        var content =
                given()
                .basePath("/swagger-ui/index.html")
                .port(8080)
                .when()
                    .get()
                .then()
                        .statusCode(200)
                .extract()
                        .body().asString();

        assertTrue(content.toLowerCase().contains("swagger ui".toLowerCase()),
                "A página não contém o texto esperado 'Swagger UI'");
    }
}
