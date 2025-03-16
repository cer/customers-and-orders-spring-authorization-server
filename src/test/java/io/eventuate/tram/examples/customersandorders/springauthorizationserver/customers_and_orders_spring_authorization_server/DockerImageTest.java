package io.eventuate.tram.examples.customersandorders.springauthorizationserver.customers_and_orders_spring_authorization_server;

import org.junit.jupiter.api.Test;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.images.builder.ImageFromDockerfile;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.nio.file.FileSystems;
import java.time.Duration;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Testcontainers
public class DockerImageTest {

    @Container
    public GenericContainer<?> container = new GenericContainer<>(
            new ImageFromDockerfile()
                    .withDockerfile(FileSystems.getDefault().getPath("./Dockerfile"))
                    .withBuildArg("baseImageVersion", "0.1.0.BUILD-SNAPSHOT")
                    .withBuildArg("serviceImageVersion", "0.1.0-SNAPSHOT")
            )
            .withExposedPorts(9000)
            .waitingFor(Wait.forListeningPort().withStartupTimeout(Duration.ofSeconds(30)));

    @Test
    public void testContainerStartsAndListensOnPort9000() {
        assertTrue(container.isRunning(), "Container should be running");

        Integer mappedPort = container.getMappedPort(9000);
        String host = container.getHost();

        given()
            .baseUri("http://" + host)
            .port(mappedPort)
        .when()
            .get("/.well-known/openid-configuration")
        .then()
            .statusCode(200);
    }
}
