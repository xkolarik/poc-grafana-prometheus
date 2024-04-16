package br.gov.bnb.employeeservice.health.custom;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.health.Status;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Endpoint(id = "customendpoint")
@Component
public class MyCustomEndpoint {

    @ReadOperation
    @Bean
    public Status getStatus() {
        // Implement the logic to decide what status this endpoint should return
        return Status.UP;
    }
}
