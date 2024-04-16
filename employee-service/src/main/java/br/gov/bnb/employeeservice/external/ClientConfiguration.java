package br.gov.bnb.employeeservice.external;

import org.springframework.context.annotation.Bean;

import br.gov.bnb.employeeservice.exceptions.ChildNotFoundException;
import feign.Response;
import feign.codec.ErrorDecoder;

public class ClientConfiguration {
    @Bean
    public ErrorDecoder errorDecoder() {
        return new CustomErrorDecoder();
    }

    static class CustomErrorDecoder implements ErrorDecoder {
        @Override
        public Exception decode(String methodKey, Response response) {
            switch (response.status()){
                case 404:
                    return new ChildNotFoundException();
                default:
                    return new Exception("Generic error");
            }
        }
    }
}
