FROM openjdk:11 as builder

WORKDIR /build

COPY ./mvnw* ./pom.xml ./
COPY ./.mvn ./.mvn

RUN ./mvnw dependency:go-offline

COPY ./src/ ./src/

RUN ./mvnw clean package

RUN java -Djarmode=layertools -jar ./target/employee-service-0.0.1.jar extract

FROM openjdk:11

COPY --from=builder /build/dependencies/ ./
COPY --from=builder /build/spring-boot-loader/ ./
COPY --from=builder /build/snapshot-dependencies/ ./
COPY --from=builder /build/application/ ./

ARG SERVER_PORT=8080

EXPOSE $SERVER_PORT

ENV _JAVA_OPTIONS "-XX:MinRAMPercentage=60.0 -XX:MaxRAMPercentage=90.0 \
-Djava.security.egd=file:/dev/./urandom \
-Djava.awt.headless=true -Dfile.encoding=UTF-8 \
-Dspring.output.ansi.enabled=ALWAYS"

ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher"]
