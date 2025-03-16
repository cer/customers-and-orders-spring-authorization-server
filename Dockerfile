ARG baseImageVersion
FROM eventuateio/eventuate-examples-docker-images-spring-example-base-image:$baseImageVersion
ARG serviceImageVersion
COPY build/libs/customers-and-orders-spring-authorization-server-$serviceImageVersion.jar service.jar
HEALTHCHECK --interval=3s --retries=20 CMD curl -f http://localhost:9000/actuator/health || exit 1
