package br.gov.bnb.childservice.monitoring;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.prometheus.client.exemplars.tracer.otel_agent.OpenTelemetryAgentSpanContextSupplier;

@Configuration
public class PrometheusExemplarSamplerConfiguration {
    @Bean
    @ConditionalOnClass(name = "io.opentelemetry.javaagent.shaded.io.opentelemetry.api.trace.Span")
    public OpenTelemetryAgentSpanContextSupplier openTelemetryAgentSpanContextSupplier() {
        // OpenTelemetryAgentSpanContextSupplier is from the opentelemetry agent jar, without using the agent will cause class not found error when running.
        return new OpenTelemetryAgentSpanContextSupplier();
    }
}
